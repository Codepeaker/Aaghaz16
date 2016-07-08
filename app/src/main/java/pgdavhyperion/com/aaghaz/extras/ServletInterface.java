package pgdavhyperion.com.aaghaz.extras;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ServletInterface {

    public static String executeHttpRequest(String college, String event, String nom, String name, String pno, String course, String email)//Send Message
    {
        final String SERVER_URL = "http://www.pgdavhyperion.com/form-1/regapp.php"; // The server(Servlet) which will process the request. Note 10.0.2.2 is the localhost IP for emulator
        final String CHAR_SET = "UTF-8"; // Encoding used for the parameters
        OutputStream output = null;
        String response = "";

        String[] names = name.split(" ");
        name = "";
        int i = 1;
        while (i < (names.length)) {
            name = name + " " + names[i++];
        }

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            // ------------------------------------------------------START: PREPARE CONNECTION AND REQUEST ------------------------------- //
            String data = URLEncoder.encode("firstname", CHAR_SET) + "=" + URLEncoder.encode(names[0], CHAR_SET);
            if (names.length > 1)
                data += "&" + URLEncoder.encode("lastname", CHAR_SET) + "=" + URLEncoder.encode(name.trim(), CHAR_SET);
            else
                data += "&" + URLEncoder.encode("lastname", CHAR_SET) + "=" + URLEncoder.encode(" ", CHAR_SET);
            data += "&" + URLEncoder.encode("email", CHAR_SET) + "=" + URLEncoder.encode(email, CHAR_SET);
            data += "&" + URLEncoder.encode("mobileno", CHAR_SET) + "=" + URLEncoder.encode(pno, CHAR_SET);
            data += "&" + URLEncoder.encode("collegename", CHAR_SET) + "=" + URLEncoder.encode(college, CHAR_SET);
            data += "&" + URLEncoder.encode("eventname", CHAR_SET) + "=" + URLEncoder.encode(event, CHAR_SET);
            data += "&" + URLEncoder.encode("membersno", CHAR_SET) + "=" + URLEncoder.encode(nom, CHAR_SET);

            URLConnection connection = new URL(SERVER_URL).openConnection(); // Create a connection to server using URL
            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);

            output = null;
            output = connection.getOutputStream(); //open a Output stream from the connection for posting data
            output.write(data.getBytes(CHAR_SET)); //Post data
            output.flush();
            output.close();
            //------------------------------------------------------ END: PREPARE CONNECTION AND REQUEST --------------------------------- //

            InputStream responseStream = connection.getInputStream(); //This is when the request is actually fired

            //------------------------------------------------------ START: READ RESPONSE ------------------------------------------------ //
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream, CHAR_SET)); // Connect a BufferReader to the inputStream
            String line = null;

            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }
            //------------------------------------------------------ END: READ RESPONSE ------------------------------------------------- //
        } catch (UnsupportedEncodingException e) {
            response = response + e.getMessage();
            e.printStackTrace();

        } catch (IOException io) {
            response = response + io.getMessage();
        } finally {
            if (output != null) try {
                output.close();
            } catch (IOException ignoreIO) {
            }
        }
        return response;
    }
}