
/**
 * SELECT TAG
 */
AjaxJspTag.SelectOptGroup = Class.create();
AjaxJspTag.SelectOptGroup.prototype = Object.extend(new AjaxJspTag.Base(), {

  initialize: function(url, options) {
    this.url = url;
    this.setOptions(options);
    this.setListeners();

    if (parseBoolean(this.options.executeOnLoad)) {
      this.execute();
    }
   addAjaxListener(this);
  },
  reload: function () {
    this.setListeners();
  },
  
  setOptions: function(options) {
    this.options = Object.extend({
      parameters: options.parameters || '',
      doPost: options.doPost || false,
      emptyOptionValue: options.emptyOptionValue || '',
      emptyOptionName:  options.emptyOptionName || '',
      eventType: options.eventType ? options.eventType : "change",
      parser: options.parser ? options.parser : new ResponseXmlParser(),
      handler: options.handler ? options.handler : this.handler
    }, options || {});
  },

  setListeners: function() {
  $(this.options.source).ajaxSelect = this; 
  
    Event.observe($(this.options.source),
      this.options.eventType,
      this.execute.bindAsEventListener(this),
      false);
    eval("$(this.options.source).on"+this.options.eventType+" = function(){return false;};");
  },

  execute: function(e) {
    if (isFunction(this.options.preFunction)) {this.options.preFunction();}
	if (this.options.cancelExecution) {
	    this.cancelExecution = false;
	    return ;
      }
    // parse parameters and do replacements
    var params = buildParameterString(this.options.parameters);

    var obj = this; // required because 'this' conflict with Ajax.Request
    var aj = new Ajax.Request(this.url, {
      asynchronous: true,
      method: obj.options.doPost ? 'post':'get',
      evalScripts: true,
      parameters: params,
      onSuccess: function(request) {
        obj.options.parser.load(request);
        var results = obj.options.parser.itemList;
        
        
        
        obj.options.handler(request, {target: obj.options.target,
                                      items: results });
      },
      onFailure: function(request) {
        if (isFunction(obj.options.errorFunction)){ obj.options.errorFunction();}
      },
      onComplete: function(request) {
        if (isFunction(obj.options.postFunction)) {obj.options.postFunction();}
      }
    });
  },

  handler: function(request, options) {
    // build an array of option values to be set as selected
    
    $(options.target).options.length = 0;
    $(options.target).disabled = false;
    
    while ($(options.target).firstChild) 
    {
	  	$(options.target).removeChild($(options.target).firstChild);
	}
    	 
	var optGroup = "";
	var bAddSuffixInValue;
	var groupCounter = 0;
	  
	for (var i=0; i<options.items.length; i++)
	{
		if (i > 0)
		{
			if (optGroup != options.items[i][0])
    		{
    			var objOptGrp = document.createElement("OPTGROUP");
	    		objOptGrp.label = options.items[i][0];
	    		$(options.target).appendChild(objOptGrp);
	    		
	    		groupCounter++;
    		}
			
			optGroup = options.items[i][0];
		}
		else
		{
			bAddSuffixInValue = options.items[i][0];
		}
		
		
		
		var newOption = new Option(options.items[i][1], (options.items[i][2] + ((bAddSuffixInValue == "true") ? ("_GRP" + groupCounter) : "")));
		
		// set the option as selected if it is in the default list
		if ( newOption.selected == false && options.items[i].length == 4 && parseBoolean(options.items[i][3]) ){
           newOption.selected = true;
      }
    
		$(options.target).options[i] = newOption;
    }
    
    if (options.items.length == 0)
    {
      $(options.target).options[i] = new Option(this.emptyOptionName, this.emptyOptionValue);
    	$(options.target).disabled = true;
    }
    // auch ein SELECT TAG ?
   	if ($(options.target).ajaxSelect && $(options.target).ajaxSelect.execute)
   	{
   		$(options.target).ajaxSelect.execute();
   	}
  }

});