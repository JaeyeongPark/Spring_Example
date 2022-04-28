package com.ssafy.word.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="관심사정보",description = "관심사 단어,~~")
public class WordDto {
	@ApiModelProperty(value = "관심단어")
	private String text;
	@ApiModelProperty(value = "관심도")
	private double weight;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
