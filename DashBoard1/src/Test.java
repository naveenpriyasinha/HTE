import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Test {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		    Map<String ,Integer>  strMap = new  HashMap<>();
			String arr[]= {"Naveen","Shubhnagi","Shubhnagi","Shiva"};
			List<String> list = Arrays.asList(arr);
			String temp="";
			int count=0;
			for(int i=0;i<arr.length;i++) {
				for (int j=0;j<arr.length;j++) {
						if(arr[i]==arr[j]) {
							count++;
							temp=arr[i];
							strMap.put(temp, count);
						}
				}
				count=0;
			}
			System.out.println("String "+temp);
			System.out.println("String "+strMap.toString()); 
			 Collections.max(strMap.entrySet(),Comparator.comparingInt(Map.Entry::getValue)).getKey();
			 Collections.max(strMap.entrySet(),Comparator.comparingInt(Map.Entry::getValue)).getValue();
			 String key=Collections.max(strMap.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
			System.out.println("String "+key);  
			System.out.println("String assa"+list.stream().collect(Collectors.toMap(Function.identity(), v -> 1L, Long::sum)));
			System.out.println("String asa"+Collectors.counting());
			System.out.println("String Naven"+list.stream().collect(Collectors.groupingBy(k -> k, Collectors.counting())));
			
			Test ttt= new Test();
			
			System.out.println(
		            "Entry with highest value key: "+ttt.getMaxEntryInMapBasedOnValue(strMap));
			
	}

	
	public <K, V extends Comparable<V>> Map.Entry<K, V>  getMaxEntryInMapBasedOnValue(Map<K, V> map) {
		Map.Entry<K, V> maxMap=null; 
		 for (Map.Entry<K, V> currentEntry :
             map.entrySet()) {
				if(maxMap==null ||   currentEntry.getValue().compareTo( maxMap.getValue()) > 0) {
					maxMap = currentEntry;
				}
		 }
		
		return maxMap;
	}
}
