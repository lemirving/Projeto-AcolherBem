package com.project.project_healtheducation.manager;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.project.project_healtheducation.model.EmotionalStatus;

public class EmotionalStatusManager {

    public static Map<Month, List<EmotionalStatus>> agruparPorMes(List<EmotionalStatus> registros) {
        return registros.stream().collect(Collectors.groupingBy(e -> e.getData().getMonth()));
    }
}