package com.advex2.interceptor2.months;

import com.advex2.interceptor2.months.Month;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class MonthInterceptor implements HandlerInterceptor {
    @Autowired
    private Month month;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<Month> semester = Arrays.asList(new Month(2, "February", "Febbraio", "Februar"),
                new Month(3, "March", "Marzo", "März"),
                new Month(4, "April", "Aprile", "April"),
                new Month(5, "May", "Maggio", "Mai"),
                new Month(6, "June", "Giugno", "Juni"),
                new Month(7, "July", "Luglio", "Juli"));

        String monthNumberHeader = request.getHeader("monthNumber");

        if (monthNumberHeader == null || monthNumberHeader.isEmpty()) {
            response.sendError(405, "Bad Request");
            System.out.println(HttpStatus.BAD_REQUEST);}

        Month foundMonth = semester.stream()
                .filter(m -> m.getMonthNumber().equals(month.getMonthNumber()))
                .findFirst()
                .orElse(new Month(0, "nope", "nope", "nope"));

        System.out.println(HttpStatus.OK);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView view
    ) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, view);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
