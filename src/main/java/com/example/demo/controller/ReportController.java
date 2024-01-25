package com.example.demo.controller;


import com.example.demo.model.LookupModel;
import com.example.demo.model.ReportModel;
import com.example.demo.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/reports")
public class ReportController {

/*    @Autowired
    private ReportService reportService;*/

    @Autowired
    private LookupService lookupService;

    @GetMapping("/registration-report")
    public ModelAndView registrationReport(LookupModel lookupModel) {
        System.out.printf("report method");
        ModelAndView modelAndView = new ModelAndView("reportExample");

        modelAndView.addObject("formName", "Registration Information");
        modelAndView.addObject("branchList", lookupService.branchList());

        return modelAndView;
    }

//    @PostMapping("/view-report")
//    public ModelAndView viewreport(ModelAndView modelAndView, @ModelAttribute("reportModel") ReportModel reportModel,
//                                   BindingResult result, HttpSession session, HttpServletRequest request, HttpServletResponse response,
//                                   final RedirectAttributes redirectAttributes)
//            throws Exception {
//        String bName = (String) session.getAttribute("branchName");
//        JasperReportsPdfView pdf = new JasperReportsPdfView();
//        JasperReportsXlsView xls = new JasperReportsXlsView();
//        Map<String, Object> params = new HashMap<String, Object>();
//        ReportModel oReportDetails = new ReportModel();
//
//        if (reportModel.getReportCode().equals("1001")) {
////                oReportDetails = reportService.searchReportDetails(reportModel);
//            oReportDetails.setJesperName("WEB-INF//reports//sampleReport.jasper");
//
//            String path = request.getSession().getServletContext()
//                    .getRealPath("/WEB-INF/reports/");
//            String reportPath = path.replace("\\", "/");
//
////            params.put("SUBREPORT_DIR", reportPath + "/");
//            reportModel.setViewType("3");
//        }
//
//        if (reportModel.getViewType().equals("1")) {
//            pdf.setReportDataKey("dataSource");
//            pdf.setUrl(oReportDetails.getJesperName());
//            pdf.setApplicationContext(appContext);
//            return new ModelAndView(pdf, params);
//
//        } else if (reportModel.getViewType().equals("2")) {
//            //System.out.println("viewType 2 JesperName " + oReportDetails.getJesperName());
//            xls.setReportDataKey("dataSource");
//            xls.setUrl(oReportDetails.getJesperName());
//            xls.setApplicationContext(appContext);
//            return new ModelAndView(xls, params);
//
//        } else if (reportModel.getViewType().equals("3")) {
//            reportService.viewPdfReport(oReportDetails.getJesperName(), request, response, params);
//            return modelAndView;
//        } else {
//            return null;
//        }
//    }
}
