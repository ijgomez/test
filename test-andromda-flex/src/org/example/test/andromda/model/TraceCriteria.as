package org.example.test.andromda.model {
	
	[RemoteClass(alias="org.example.test.andromda.criteria.TraceCriteria")]
	[Bindable]
	public class TraceCriteria extends ValueObjectCriteria {
		public var message:String;
		public var type:String;
		public var initDateTime:Date;
        public var lastDateTime:Date;
	}
}