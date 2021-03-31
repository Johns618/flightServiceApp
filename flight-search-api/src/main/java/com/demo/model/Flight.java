package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Date;

import javax.persistence.Column;

@Entity
public class Flight {
	
	@Id
	int id;
	int flt_num;
	Date created_at;
	Date updated_at;
	Date out_gmt;
	Date in_gmt;
	Date off_gmt;
	Date on_gmt;		
	String flight_identifier;	
	String scheduled_origin_gate;
	String scheduled_destination_gate;	
	String destination;
	String origin;
	String destination_full_name;
	String origin_full_name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFlt_num() {
		return flt_num;
	}
	public void setFlt_num(int flt_num) {
		this.flt_num = flt_num;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public Date getOut_gmt() {
		return out_gmt;
	}
	public void setOut_gmt(Date out_gmt) {
		this.out_gmt = out_gmt;
	}
	public Date getIn_gmt() {
		return in_gmt;
	}
	public void setIn_gmt(Date in_gmt) {
		this.in_gmt = in_gmt;
	}
	public Date getOff_gmt() {
		return off_gmt;
	}
	public void setOff_gmt(Date off_gmt) {
		this.off_gmt = off_gmt;
	}
	public Date getOn_gmt() {
		return on_gmt;
	}
	public void setOn_gmt(Date on_gmt) {
		this.on_gmt = on_gmt;
	}
	public String getFlight_identifier() {
		return flight_identifier;
	}
	public void setFlight_identifier(String flight_identifier) {
		this.flight_identifier = flight_identifier;
	}
	public String getScheduled_origin_gate() {
		return scheduled_origin_gate;
	}
	public void setScheduled_origin_gate(String scheduled_origin_gate) {
		this.scheduled_origin_gate = scheduled_origin_gate;
	}
	public String getScheduled_destination_gate() {
		return scheduled_destination_gate;
	}
	public void setScheduled_destination_gate(String scheduled_destination_gate) {
		this.scheduled_destination_gate = scheduled_destination_gate;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination_full_name() {
		return destination_full_name;
	}
	public void setDestination_full_name(String destination_full_name) {
		this.destination_full_name = destination_full_name;
	}
	public String getOrigin_full_name() {
		return origin_full_name;
	}
	public void setOrigin_full_name(String origin_full_name) {
		this.origin_full_name = origin_full_name;
	}
	
}
