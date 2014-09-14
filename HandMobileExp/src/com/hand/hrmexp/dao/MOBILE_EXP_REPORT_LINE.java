 /**
 * 
 */
package com.hand.hrmexp.dao;

import com.littlemvc.db.annotation.Id;
import com.littlemvc.db.annotation.Table;

/**
 * @author jiang titeng
 *
 * All right reserve
 */

public class MOBILE_EXP_REPORT_LINE  {
	
	@Id(column="id")
	public int id;
	public int expense_class_id;
	public String  expense_class_desc;
	public int expense_type_id;
	public String expense_type_desc; 
	public  int  expense_amount;
	public  int  expense_number;
	public  String   expense_date;
	public  String     expense_date_to;
	
	public String  expense_place;
		
    public String  description;
    public String   local_status;
    
    public  int  service_id;
    
    public  String    CREATION_DATE  ;
    public String       CREATED_BY;
    public byte[] item1;


	public MOBILE_EXP_REPORT_LINE() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getExpense_class_id() {
		return expense_class_id;
	}


	public void setExpense_class_id(int expense_class_id) {
		this.expense_class_id = expense_class_id;
	}


	public String getExpense_class_desc() {
		return expense_class_desc;
	}


	public void setExpense_class_desc(String expense_class_desc) {
		this.expense_class_desc = expense_class_desc;
	}


	public int getExpense_type_id() {
		return expense_type_id;
	}


	public void setExpense_type_id(int expense_type_id) {
		this.expense_type_id = expense_type_id;
	}


	public String getExpense_type_desc() {
		return expense_type_desc;
	}


	public void setExpense_type_desc(String expense_type_desc) {
		this.expense_type_desc = expense_type_desc;
	}


	public int getExpense_amount() {
		return expense_amount;
	}


	public void setExpense_amount(int expense_amount) {
		this.expense_amount = expense_amount;
	}


	public int getExpense_number() {
		return expense_number;
	}


	public void setExpense_number(int expense_number) {
		this.expense_number = expense_number;
	}


	public String getExpense_date() {
		return expense_date;
	}


	public void setExpense_date(String expense_date) {
		this.expense_date = expense_date;
	}


	public String getExpense_date_to() {
		return expense_date_to;
	}


	public void setExpense_date_to(String expense_date_to) {
		this.expense_date_to = expense_date_to;
	}


	public String getExpense_place() {
		return expense_place;
	}


	public void setExpense_place(String expense_place) {
		this.expense_place = expense_place;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getLocal_status() {
		return local_status;
	}


	public void setLocal_status(String local_status) {
		this.local_status = local_status;
	}


	public int getService_id() {
		return service_id;
	}


	public void setService_id(int service_id) {
		this.service_id = service_id;
	}


	public String getCREATION_DATE() {
		return CREATION_DATE;
	}


	public void setCREATION_DATE(String cREATION_DATE) {
		CREATION_DATE = cREATION_DATE;
	}


	public String getCREATED_BY() {
		return CREATED_BY;
	}


	public void setCREATED_BY(String cREATED_BY) {
		CREATED_BY = cREATED_BY;
	}


	public byte[] getItem1() {
		return item1;
	}


	public void setItem1(byte[] item1) {
		this.item1 = item1;
	}
	


	


}
