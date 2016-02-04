package com.jordanec.sbrestapistormpath.model;

import java.util.Collection;
import java.util.Iterator;

public class CustomRequRespObject<T> {
	private Collection<T> collection;
	private T object;
	private Status status;
	private boolean singleObject;
	
	public CustomRequRespObject(){}
	
	public CustomRequRespObject( boolean singleObject) {
		this.singleObject = singleObject;
	}
	
	public CustomRequRespObject(Collection<T> collection, Status status, boolean singleObject) {
		this.collection = collection;
		this.status = status;
		this.singleObject = singleObject;
	}

	public CustomRequRespObject(T object, Status status, boolean singleObject) {
		this.object = object;
		this.status = status;
		this.singleObject = singleObject;
	}
	
	public CustomRequRespObject(Collection<T> collection, boolean singleObject) {
		this.collection = collection;
		this.singleObject = singleObject;
	}
	
	public CustomRequRespObject(T object, boolean singleObject) {
		this.object = object;
		this.singleObject = singleObject;
	}
	
	
	public boolean isSingleObject() {
		return singleObject;
	}

	public void setSingleObject(boolean singleObject) {
		this.singleObject = singleObject;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setCollection(Collection<T> collection) {
		this.collection = collection;
	}

	public Collection<T> getCollection() {
		return collection;
	};
	
	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}
	
	/*
	public T getSingleObject() {
		Iterator<T> iterator = getCollection().iterator();
		if(iterator.hasNext())
			return iterator.next();
		return null;
	}*/
	
}