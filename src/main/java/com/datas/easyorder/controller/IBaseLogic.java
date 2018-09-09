package com.datas.easyorder.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.datas.utils.GuessingWord;

public interface IBaseLogic {

	/**
	 * get Guessing word List
	 * @param q
	 * @param pageRequest
	 * @return
	 */
	public List<GuessingWord> guessingWordList(String q, PageRequest pageRequest);
}
