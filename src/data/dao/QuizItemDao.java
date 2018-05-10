package data.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.model.QuizItem;

public class QuizItemDao {

    public List<QuizItem> list() throws SQLException {
        List<QuizItem> products = new ArrayList<QuizItem>();
        
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mupquiz", "root", "");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `quiz`");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	QuizItem qi = new QuizItem();
            	qi.setQuestion(resultSet.getString("question"));
            	qi.setAnswer(resultSet.getString("answer"));
                products.add(qi);
            }
        } catch (Exception e2) {
            System.out.println(e2);
        }
        
        return products;
    }

}
