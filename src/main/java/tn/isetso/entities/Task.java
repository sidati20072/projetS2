package tn.isetso.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Task {

	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id ; 
	private String taskName;
	
}
