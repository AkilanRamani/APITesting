package TestAutomation;

public class Student {
	
	private int orderNo ;  
	private String columnName;  
	private String columnCaption;  
	private String isEnable;  
	private String isEdit;  
	private String max;  
	private String type;  
	private String isReadOnly;  


	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnCaption() {
		return columnCaption;
	}

	public void setColumnCaption(String columnCaption) {
		this.columnCaption = columnCaption;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsReadOnly() {
		return isReadOnly;
	}

	public void setIsReadOnly(String isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	
	

	@Override
	public String toString() {
		return "Student [orderNo=" + orderNo + ", columnName=" + columnName + ", columnCaption=" + columnCaption
				+ ", isEnable=" + isEnable + ", isEdit=" + isEdit + ", max=" + max + ", type=" + type + ", isReadOnly="
				+ isReadOnly + "]";
	}

	public Student(){  
        
	}  

}
