package com.codekages.dto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode @ToString @NoArgsConstructor
public class AddRecipeDTO {

	private String name;
	private String description;

}
