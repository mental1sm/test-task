package com.mentalism.testtask.service;

import java.util.LinkedHashMap;

public interface IStringService {

    /**
     * Метод вычисляет количество символов в строке
     * @param target - Целевая строка
     * @return Отсортированная по убыванию значения (количество символа в строке) связанная хеш-таблица
     */
    LinkedHashMap<String, Integer> calculateCharCount(String target);
}
