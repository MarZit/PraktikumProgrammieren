package guiLager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import application.Specifications;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class CalendarPane extends GridPane {
	
	Calendar myTodayCalendar = Calendar.getInstance();
	
	public CalendarPane() {
		setUpMonth();
		setAlignment(Pos.CENTER);
		ColumnConstraints column1 = new ColumnConstraints(30, 30, 30);
	    column1.setHgrow(Priority.ALWAYS);
	    column1.setHalignment(HPos.CENTER);
		ColumnConstraints column2 = new ColumnConstraints(30, 30, 30);
	    column2.setHgrow(Priority.ALWAYS);
	    column2.setHalignment(HPos.CENTER);
		ColumnConstraints column3 = new ColumnConstraints(30, 30, 30);
	    column3.setHgrow(Priority.ALWAYS);
	    column3.setHalignment(HPos.CENTER);
		ColumnConstraints column4 = new ColumnConstraints(30, 30, 30);
	    column4.setHgrow(Priority.ALWAYS);
	    column4.setHalignment(HPos.CENTER);
		ColumnConstraints column5 = new ColumnConstraints(30, 30, 30);
		column5.setHgrow(Priority.ALWAYS);
		column5.setHalignment(HPos.CENTER);
		ColumnConstraints column6 = new ColumnConstraints(30, 30, 30);
		column6.setHgrow(Priority.ALWAYS);
		column6.setHalignment(HPos.CENTER);
		ColumnConstraints column7 = new ColumnConstraints(30, 30, 30);
		column7.setHgrow(Priority.ALWAYS);
		column7.setHalignment(HPos.CENTER);
	    getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6, column7);
	}
	
	public void setUpMonth() {
		getChildren().clear();
		setUpMonthUIHeadline();
		try {
			setUpMonthDays();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void setUpMonthUIHeadline() {
		String monthStr = myTodayCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Specifications.getInstance().getLocale());
		Button monthBackButton = new Button("<");
		monthBackButton.setOnAction(e->{
			myTodayCalendar.set(Calendar.MONTH, myTodayCalendar.get(Calendar.MONTH)-1);
			setUpMonth();
		});
		Button monthText = new Button(monthStr);
		Button monthForwardButton = new Button(">");
		monthForwardButton.setOnAction(e->{
			myTodayCalendar.set(Calendar.MONTH, myTodayCalendar.get(Calendar.MONTH)+1);
			setUpMonth();
		});
		getChildren().addAll(monthBackButton, monthText, monthForwardButton);
		GridPane.setConstraints(monthBackButton, 0, 0);
		GridPane.setConstraints(monthText, 1, 0);
		GridPane.setColumnSpan(monthText, 5);
		GridPane.setConstraints(monthForwardButton, 6, 0);
		Text sunText = new Text(Specifications.getInstance().getResources().getString("sun"));
		Text monText = new Text(Specifications.getInstance().getResources().getString("mon"));
		Text tueText = new Text(Specifications.getInstance().getResources().getString("tue"));
		Text wedText = new Text(Specifications.getInstance().getResources().getString("wed"));
		Text thuText = new Text(Specifications.getInstance().getResources().getString("thu"));
		Text friText = new Text(Specifications.getInstance().getResources().getString("fri"));
		Text satText = new Text(Specifications.getInstance().getResources().getString("sat"));
		getChildren().addAll(sunText, monText, tueText, wedText, thuText, friText, satText);
		GridPane.setConstraints(sunText, 0, 1);
		GridPane.setConstraints(monText, 1, 1);
		GridPane.setConstraints(tueText, 2, 1);
		GridPane.setConstraints(wedText, 3, 1);
		GridPane.setConstraints(thuText, 4, 1);
		GridPane.setConstraints(friText, 5, 1);
		GridPane.setConstraints(satText, 6, 1);
	}
	
	private void setUpMonthDays() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = formatter.parse(myTodayCalendar.get(Calendar.YEAR) + "-" 
				+ myTodayCalendar.get(Calendar.MONTH) + "-" 
				+ myTodayCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date endDate = formatter.parse(myTodayCalendar.get(Calendar.YEAR) + "-" 
				+ myTodayCalendar.get(Calendar.MONTH) + "-" 
				+ myTodayCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		GregorianCalendar start = new GregorianCalendar();
		start.setTime(startDate);

		GregorianCalendar end = new GregorianCalendar();
		end.setTime(endDate);
		
//		System.out.println(myTodayCalendar.get(Calendar.YEAR) + "-" 
//				+ myTodayCalendar.get(Calendar.MONTH) + "-" 
//				+ myTodayCalendar.getActualMinimum(Calendar.DAY_OF_MONTH) + "   -   " +
//				myTodayCalendar.get(Calendar.YEAR) + "-" 
//				+ myTodayCalendar.get(Calendar.MONTH) + "-" 
//				+ myTodayCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//		System.out.println(end.get(Calendar.MONTH) + " " + end.get(Calendar.DAY_OF_WEEK));
		
		int numberOfDays = myTodayCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int startDayOfWeek = start.get(Calendar.DAY_OF_WEEK);
		for(int i = 1; i <= numberOfDays; i++){
			int dayOfWeek = (startDayOfWeek + i)%7;
		    CalendarDay cd = new CalendarDay(i);
		    getChildren().add(cd);
		    GridPane.setConstraints(cd, dayOfWeek, (startDayOfWeek+i)/7 +2);
		}
	}
}
