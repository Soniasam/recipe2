 package com.codekages.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "List_Of_Recipes")
@Getter@Setter@NoArgsConstructor@EqualsAndHashCode@ToString
public class ListOfRecipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "list_of_recipes")
	private String listofrecipe;
	
	@ManyToOne
	@JoinColumn(name = "User_Id")
	private User user;
	


	public ListOfRecipe(String listofrecipe, User user) {
		super();
		this.listofrecipe = listofrecipe;
		this.user = user;
	}
	

	
	

}
