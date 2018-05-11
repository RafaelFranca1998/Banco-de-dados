package com.model;


public class Admin {
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean comparePasswrd(String senha1, String senha2) {
		int s1 = Integer.parseInt(senha1);
		int s2 = Integer.parseInt(senha2);
	if (s1 == -1) {
		return false;
					}
	if (s1 != s2) {
		return true;
					}
	return false;
}
}
