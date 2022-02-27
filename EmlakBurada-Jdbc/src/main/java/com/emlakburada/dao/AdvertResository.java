package com.emlakburada.dao;

import com.emlakburada.entity.Advert;
import com.emlakburada.entity.RealEstate;
import com.emlakburada.entity.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdvertResository extends JdbcConnectionRepository{

    private static final String INSERT_ADVERT = "INSERT INTO ADVERT (advertNo,baslik) VALUES (?,?);";
    private static final String SELECT_ALL_ADVERT = "SELECT * FROM ADVERT";
    private static final String FIND_ADVERT = "SELECT * FROM ADVERT WHERE id = ?";

    public void save(Advert advert) {

        Connection connection = connect();

        if (connection != null) {

            PreparedStatement prepareStatement = null;
            try {

                prepareStatement = connection.prepareStatement(INSERT_ADVERT);
                prepareStatement.setInt(1, advert.getAdvertNo());
                prepareStatement.setString(2, advert.getBaslik());

                int executeUpdate = prepareStatement.executeUpdate();

                System.out.println("result: " + executeUpdate);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    prepareStatement.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Connection oluşturululamadı!");
        }

    }

    public List<Advert> findAll() {

        List<Advert> advertList = new ArrayList<Advert>();

        Connection connection = connect();

        try {
            PreparedStatement prepareStatement = connection.prepareStatement(SELECT_ALL_ADVERT);

            ResultSet result = prepareStatement.executeQuery();

            while (result.next()) {

                int advertNo = result.getInt("id");
                String title = result.getString("baslik");

                advertList.add(prepareAdvert(advertNo, title));

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return advertList;

    }

    private Advert prepareAdvert(int advertNo, String baslik) {
        Advert advert = new Advert();
        advert.setAdvertNo(advertNo);
        advert.setBaslik(baslik);
        advert.setAktifMi(true);
        advert.setOlusturulmaTarihi(now());

        return advert;
    }

    public Date now(){

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        return date;
    }

    public Advert findOne(int id) {

        Advert advert = null;

        Connection connection = connect();

        try {
            PreparedStatement prepareStatement = connection.prepareStatement(FIND_ADVERT);
            prepareStatement.setInt(1, id);

            ResultSet result = prepareStatement.executeQuery();
            if (result.next()) {
                int advertNo = result.getInt("id");
                String title = result.getString("baslik");

                advert = prepareAdvert(advertNo, title);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return advert;

    }


}
