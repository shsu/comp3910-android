package ca.bcit.turnip;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import ca.bcit.turnip.domain.QuizQuestion;

public class QuizAdapter extends ArrayAdapter<QuizQuestion> {

	private List<QuizQuestion> questions;
	private static LayoutInflater inflater = null;

	public QuizAdapter(Activity activity, int textViewResourceId,
			List<QuizQuestion> _questions) {
		super(activity, textViewResourceId, _questions);
		try {
			this.questions = _questions;

			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		} catch (Exception e) {

		}
	}

	@Override
	public int getCount() {
		return questions.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		MyViewHolder holder = null;
		try {
			if (vi == null) {
				vi = inflater.inflate(R.layout.rowlayout, null);
				holder = new MyViewHolder();

				holder.question_text = (TextView) vi
						.findViewById(R.id.TextView01);
				holder.answer_options = (RadioGroup) vi
						.findViewById(R.id.RadioGroup01);
				holder.a = (RadioButton) vi.findViewById(R.id.RadioButton01);
				holder.b = (RadioButton) vi.findViewById(R.id.RadioButton02);
				holder.c = (RadioButton) vi.findViewById(R.id.RadioButton03);
				holder.d = (RadioButton) vi.findViewById(R.id.RadioButton04);

				
				vi.setTag(holder);

				holder.answer_options
						.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

							@Override
							public void onCheckedChanged(RadioGroup group,
									int checkedId) {
								// TODO Auto-generated method stub
								Integer pos = (Integer) group.getTag();

								QuizQuestion question = questions.get(pos);
								switch (checkedId) {
								case R.id.RadioButton01:
									question.setSelectedAnswer('A');
									break;
								case R.id.RadioButton02:
									question.setSelectedAnswer('B');
									break;
								case R.id.RadioButton03:
									question.setSelectedAnswer('C');
									break;
								case R.id.RadioButton04:
									question.setSelectedAnswer('D');
									break;
								default:
									question.setSelectedAnswer(null);
								}

								if (question.getSelectedAnswer() != null) {
									Log.d("selectedAnswer", question
											.getSelectedAnswer().toString());
								}

							}
						});

			} else {
				holder = (MyViewHolder) vi.getTag();
			}

		} catch (Exception e) {
			Log.e("getView", e.toString());
		}
		
        holder.answer_options.setTag(new Integer(position)); // I passed the current position as a tag
        
        holder.question_text.setText(questions.get(position)
				.getQuestionNumber()
				+ ". "
				+ questions.get(position).getQuestion());
		holder.a.setText(questions.get(position).getChoiceA());
		holder.b.setText(questions.get(position).getChoiceB());
		holder.c.setText(questions.get(position).getChoiceC());
		holder.d.setText(questions.get(position).getChoiceD());
/**		holder.answer_options.addView(holder.a);
		holder.answer_options.addView(holder.b);
		holder.answer_options.addView(holder.c);
		holder.answer_options.addView(holder.d);
**/
       

        if (questions.get(position).getSelectedAnswer() != null) {
            RadioButton r = (RadioButton) holder.answer_options.getChildAt(questions
                    .get(position).getSelectedAnswer());
            r.setChecked(true);
        } else {
            holder.answer_options.clearCheck(); // This is required because although the Model could have the current 
                                       // position to NONE you could be dealing with a previous row where
                                       // the user already picked an answer. 

        }
		
		
		return vi;
	}

	private static class MyViewHolder {
		TextView question_text;
		RadioGroup answer_options;
		RadioButton a;
		RadioButton b;
		RadioButton c;
		RadioButton d;
	}

}
