package com.mentalism.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StringService implements IStringService {


    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public LinkedHashMap<String, Integer> calculateCharCount(String target) {
        LinkedHashMap<String, Integer> charactersCount = new LinkedHashMap<>();

        for (char ch : target.toCharArray()) {
            String _ch = String.valueOf(ch);

            if (!charactersCount.containsKey(_ch)) {
                charactersCount.put(_ch, 1);
            }
            else {
                charactersCount.replace(_ch, charactersCount.get(_ch) + 1);
            }
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(charactersCount.entrySet());
        entryList.sort(Map.Entry.comparingByKey());
        entryList.sort(Comparator.comparing(entry -> ((Map.Entry<String, Integer>) entry).getValue()).reversed());

        LinkedHashMap<String, Integer> resultMap = new LinkedHashMap<>();

        for (Map.Entry<String, Integer> entry : entryList) {
            resultMap.put(entry.getKey(), entry.getValue());
        }

        return resultMap;
    }

}
