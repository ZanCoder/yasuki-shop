package com.poly.Yasuki.filter;

import com.poly.Yasuki.entity.GroupCategory;
import com.poly.Yasuki.service.GroupCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GlobalDataFilter implements  Filter {
    private final GroupCategoryService groupCategoryService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest hRequest = (HttpServletRequest) request;

        if (hRequest.getSession().getAttribute("dataCategory") == null) {
            List<GroupCategory> myCategoryList = groupCategoryService.getAllCategoryGroupIsActive();
            hRequest.getSession().setAttribute("dataCategory", myCategoryList);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
