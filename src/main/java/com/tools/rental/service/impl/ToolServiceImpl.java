package com.tools.rental.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.tools.rental.model.entity.ToolDetailsBean;
import com.tools.rental.service.ToolService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ToolServiceImpl implements ToolService {


    private final Map<String, ToolDetailsBean> toolDetailsBeansMap;


    @Override
    public ToolDetailsBean toolDetailsByCode(String code) {
        return toolDetailsBeansMap.get(String.valueOf(code));
    }


}
