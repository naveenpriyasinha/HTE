package com.tcs.sgv.eis.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.transform.BasicTransformerAdapter;

@SuppressWarnings( { "unchecked", "serial" })
public class AliasToEntityMapResult extends BasicTransformerAdapter implements Serializable
{
	public AliasToEntityMapResult()
	{}

	public Object transformTuple(Object tuple[], String aliases[])
	{
		Map result = new HashMap(tuple.length);
		for (int i = 0; i < tuple.length; i++)
		{
			String alias = aliases[i];
			if (alias != null)
				result.put(alias, tuple[i]);
		}

		return result;
	}

	private Object readResolve()
	{
		return INSTANCE;
	}

	public boolean equals(Object other)
	{
		return other != null && (com.tcs.sgv.eis.service.AliasToEntityMapResult.class).isInstance(other);
	}

	public int hashCode()
	{
		return getClass().getName().hashCode();
	}

	public static final AliasToEntityMapResult INSTANCE = new AliasToEntityMapResult();
}
