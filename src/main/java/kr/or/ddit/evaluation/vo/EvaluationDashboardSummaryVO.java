package kr.or.ddit.evaluation.vo;

import lombok.Data;

@Data
public class EvaluationDashboardSummaryVO {
	private int totalTarget;
    private int completed;
    private int notCompleted;
    private double completionRate;
}
