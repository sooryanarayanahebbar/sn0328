package com.tools.rental.admin.tool;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ToolServiceImpl implements ToolService {


    private final Map<String, ToolDetailsBean> toolDetailsBeansMap;

    public List<ToolDetailsBean> toolDetails() {
        List<ToolDetailsBean> entities = new ArrayList<ToolDetailsBean>(toolDetailsBeansMap.size());
        entities.addAll(toolDetailsBeansMap.values());
        return entities;
    }

    @Override
    public ToolDetailsBean toolDetailsByCode(String code) {
        return toolDetailsBeansMap.get(String.valueOf(code));
    }


}
