/**
 * 
 */
package com.hand.hrmexp.dao;

import com.littlemvc.db.annotation.Id;

/**
 * @author jiang titeng
 * 
 *         All right reserve
 */

public class MOBILE_EXP_REPORT_LINE {

	@Id(column = "id")
	public int id;
	public int expense_class_id;
	public String expense_class_desc;
	public int expense_type_id;
	public String expense_type_desc;
	public float expense_amount;
	public int expense_number;

	public float total_amount;

	public String expense_date;
	public String expense_date_to;

	public String expense_place;

	public String description;
	public String local_status;

	public int service_id;

	public String CREATION_DATE;
	public String CREATED_BY;
	public byte[] item1;
	public byte[] item2;
	public byte[] item3;
	public byte[] item4;
	public byte[] item5;
	public byte[] item6;
	public byte[] item7;
	public byte[] item8;
	public byte[] item9;
	public byte[] item10;
	public byte[] item11;
	public byte[] item12;
	public byte[] item13;
	public byte[] item14;
	public byte[] item15;
	public int segment_1;
	public int segment_2;
	public int segment_3;
	public int segment_4;
	public int segment_5;
	public int segment_6;
	public int segment_7;
	public int segment_8;
	public int segment_9;
	public int segment_10;

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

	public float getExpense_amount() {
		return expense_amount;
	}

	public void setExpense_amount(float expense_amount) {
		this.expense_amount = expense_amount;
	}

	public float getTotal_amount() {

		return total_amount;
	}

	public void setTotal_amount(float total_amount) {

		this.total_amount = total_amount;
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

	public byte[] getItem2() {
		return item2;
	}

	public void setItem2(byte[] item2) {
		this.item2 = item2;
	}

	public byte[] getItem3() {
		return item3;
	}

	public void setItem3(byte[] item3) {
		this.item3 = item3;
	}
	
	public byte[] getItem4() {
		return item4;
	}

	public void setItem4(byte[] item4) {
		this.item4 = item4;
	}
	
	public byte[] getItem5() {
		return item1;
	}

	public void setItem5(byte[] item1) {
		this.item1 = item1;
	}
}
