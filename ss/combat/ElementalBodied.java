package com.thd.ss.combat;

import java.util.Set;

public interface ElementalBodied 
{
	ElementType getPrimaryBodyElement();
	
	Set<ElementType> getBodyElements();
}
