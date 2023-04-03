package com.github.codergate.services.utility;

import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.entities.ThresholdEntity;
import org.springframework.stereotype.Component;

@Component
public class PullRequestUtil {

    /**
     * method compares latest analysis with threshold values for a repo
     * 
     * @param analysisEntity
     * @param thresholdEntity
     * @return Boolean
     */
    public Boolean checkThreshold(AnalysisEntity analysisEntity, ThresholdEntity thresholdEntity) {
        return analysisEntity.getCodeSmell() > thresholdEntity.getCodeSmell() ||
                analysisEntity.getCyclomaticComplexity() > thresholdEntity.getCyclomaticComplexity() ||
                analysisEntity.getDuplicatedLines() > thresholdEntity.getDuplicatedLines() ||
                analysisEntity.getArchSmellDensity() > thresholdEntity.getArchSmellDensity() ||
                analysisEntity.getDesignSmellDensity() > thresholdEntity.getDesignSmellDensity() ||
                analysisEntity.getImpSmellDensity() > thresholdEntity.getImpSmellDensity() ||
                analysisEntity.getComplexConditional() > thresholdEntity.getComplexConditional() ||
                analysisEntity.getCyclicallyDependentModularization() > thresholdEntity.getCycDependentMod() ||
                analysisEntity.getCyclicDependency() > thresholdEntity.getCyclicDependency() ||
                analysisEntity.getEmptyCatchClause() > thresholdEntity.getEmptyCatchClause() ||
                analysisEntity.getEmptyTest() > thresholdEntity.getEmptyTest() ||
                analysisEntity.getGodComponents() > thresholdEntity.getGodComponents() ||
                analysisEntity.getInsufficientModularization() > thresholdEntity.getInsufficientModularization() ||
                analysisEntity.getMissingAssertion() > thresholdEntity.getMissingAssertion() ||
                analysisEntity.getUnnecessaryAbstraction() > thresholdEntity.getUnnecessaryAbstraction();
    }
}
