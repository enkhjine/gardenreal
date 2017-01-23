package hospital.businessentity;

import hospital.entity.ExaminationValueQuestion;

import java.util.List;

public class ExaminationWithValue {

	private ExaminationValueQuestion question;
	private boolean isBoolean;
	private boolean isSelectable;
	private boolean isInput;

	public ExaminationValueQuestion getQuestion() {
		return question;
	}

	public void setQuestion(ExaminationValueQuestion question) {
		this.question = question;
	}

	public boolean isBoolean() {
		return isBoolean;
	}

	public void setBoolean(boolean isBoolean) {
		this.isBoolean = isBoolean;
	}

	public boolean isSelectable() {
		return isSelectable;
	}

	public void setSelectable(boolean isSelectable) {
		this.isSelectable = isSelectable;
	}

	public boolean isInput() {
		return isInput;
	}

	public void setInput(boolean isInput) {
		this.isInput = isInput;
	}

	public ExaminationWithValue(ExaminationValueQuestion question) {
		super();
		this.question = question;
	}

}
