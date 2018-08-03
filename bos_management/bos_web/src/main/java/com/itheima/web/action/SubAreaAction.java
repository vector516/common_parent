package com.itheima.web.action;

import com.itheima.bos.service.SubAreaService;
import com.itheima.domain.SubArea;
import com.itheima.utils.Config;
import com.itheima.utils.FileUtil;
import com.itheima.utils.FileUtils;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class SubAreaAction extends BaseAction<SubArea> {

    @Autowired
    private SubAreaService subAreaService;


    @Action(value = "SubareaAction_save", results = {@Result(name = "success", location = "/pages/base/sub_area.html")})
    public String save() {
        //分区的主键id为String类型,所以必须手动设置id,使用uuid
        model.setId(UUID.randomUUID().toString());
        subAreaService.save(model);
        return SUCCESS;
    }

    private String decidedzoneid;

    public void setDecidedzoneid(String decidedzoneid) {
        this.decidedzoneid = decidedzoneid;
    }

    @Action(value = "subAreaAction_findSubByFixed")
    public String findSubByFixed() throws IOException {
        List<SubArea> list = subAreaService.findByDecideId(decidedzoneid);
        //只要有的字段就可以排除,不一定是该类的,可以是多个层级下的某个字段
        listToJson(list, new String[]{"fixedArea", "subareas"});
        return NONE;
    }


    @Action(value = "subAreaAction_exportXls")
    public String exportXls() throws IOException {

        List<SubArea> list = subAreaService.findAll();

        //创建excel对象
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //创建sheet页
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("分区数据统计");
        //创建标题行
        HSSFRow row = hssfSheet.createRow(0);

        // 设置列宽
        hssfSheet.setColumnWidth(0, 6500);
        hssfSheet.setColumnWidth(1, 4000);
        hssfSheet.setColumnWidth(2, 4000);
        hssfSheet.setColumnWidth(3, 4000);
        hssfSheet.setColumnWidth(4, 4000);
        hssfSheet.setColumnWidth(5, 6500);
//        // Sheet样式
//        HSSFCellStyle sheetStyle = hssfWorkbook.createCellStyle();
//        // 背景色的设定
//        sheetStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
//        // 前景色的设定
//        sheetStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
//        // 填充模式
//        sheetStyle.setFillPattern(HSSFCellStyle.FINE_DOTS);
//        // 设置列的样式
//        for (int i = 0; i <= 14; i++) {
//            hssfSheet.setDefaultColumnStyle((short) i, sheetStyle);
//        }
        // 设置字体
        HSSFFont headfont = hssfWorkbook.createFont();
        headfont.setFontName("黑体");
        headfont.setFontHeightInPoints((short) 22);// 字体大小
        headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
        // 另一个样式
        HSSFCellStyle headstyle = hssfWorkbook.createCellStyle();
        headstyle.setFont(headfont);
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        headstyle.setLocked(true);
        headstyle.setWrapText(true);// 自动换行
        // 另一个字体样式
        HSSFFont columnHeadFont = hssfWorkbook.createFont();
        columnHeadFont.setFontName("宋体");
        columnHeadFont.setFontHeightInPoints((short) 10);
        columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 列头的样式
        HSSFCellStyle columnHeadStyle = hssfWorkbook.createCellStyle();
        columnHeadStyle.setFont(columnHeadFont);
        columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        columnHeadStyle.setLocked(true);
        columnHeadStyle.setWrapText(true);
        columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
        columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
        columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
        columnHeadStyle.setBorderRight((short) 1);// 边框的大小
        columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
        // 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
        columnHeadStyle.setFillForegroundColor(HSSFColor.WHITE.index);

        HSSFFont font = hssfWorkbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        // 普通单元格样式
        HSSFCellStyle style = hssfWorkbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);// 上下居中
        style.setWrapText(true);
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        style.setBorderLeft((short) 1);
        style.setRightBorderColor(HSSFColor.BLACK.index);
        style.setBorderRight((short) 1);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
        style.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．

        // 另一个样式
        HSSFCellStyle centerstyle = hssfWorkbook.createCellStyle();
        centerstyle.setFont(font);
        centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        centerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
        centerstyle.setWrapText(true);
        centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);
        centerstyle.setBorderLeft((short) 1);
        centerstyle.setRightBorderColor(HSSFColor.BLACK.index);
        centerstyle.setBorderRight((short) 1);
        centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
        centerstyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
        centerstyle.setFillForegroundColor(HSSFColor.WHITE.index);// 设置单元格的背景颜色．


        //创建标题行的内容
        row.setRowStyle(headstyle);

        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("分区起始号");
        row.createCell(2).setCellValue("分区终止号");
        row.createCell(3).setCellValue("分区关键字");
        row.createCell(4).setCellValue("辅助关键字");
        row.createCell(5).setCellValue("区域信息");

        //根据查到的list创建数据行
        for (SubArea subArea : list) {
            //注意getLastRowNum是指表中有几行数据就返回几-1,如果只有一行则返回0;
            HSSFRow dataRow = hssfSheet.createRow(hssfSheet.getLastRowNum() + 1);
            dataRow.setRowStyle(centerstyle);
            dataRow.createCell(0).setCellValue(subArea.getId());
            dataRow.createCell(1).setCellValue(subArea.getStartNum());
            dataRow.createCell(2).setCellValue(subArea.getEndNum());
            dataRow.createCell(3).setCellValue(subArea.getKeyWords());
            dataRow.createCell(4).setCellValue(subArea.getAssistKeyWords());
            dataRow.createCell(5).setCellValue(subArea.getArea().getName());
        }
        String fileName = "分区数据.xls";
        String header = ServletActionContext.getRequest().getHeader("User-Agent");
        String newFileName = FileUtils.encodeDownloadFilename(fileName, header);
        ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
        ServletActionContext.getResponse().setContentType("application/vnd.ms-excel;charset=UTF-8");
        ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + newFileName);
        hssfWorkbook.write(outputStream);
        hssfWorkbook.close();
//        ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
//        String filename = "分区数据统计结果.xls";
//        String agent = ServletActionContext.getRequest().getHeader("User-Agent");//客户端使用的浏览器类型
//        //处理中文文件名
//        filename = FileUtils.encodeDownloadFilename(filename, agent);
//        String mimeType = ServletActionContext.getServletContext().getMimeType(filename);
//        ServletActionContext.getResponse().setContentType(mimeType);
//        ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + filename);
//        hssfWorkbook.write(out);
//        hssfWorkbook.close();
        return NONE;
    }


    @Autowired
    private Config config;


    @Action(value = "subareaAction_downLoadTemplate")
    public String downLoadTemplate() {
        String realPath = ServletActionContext.getServletContext().getRealPath("/") + config.getTemplateFolder() + File.separator + config.getSubareaExportTemplate();
        FileUtil.download(realPath, ServletActionContext.getResponse());
        return NONE;
    }


    @Action(value = "subAreaAction_showSubAreaActionChart")
    public String showSubAreaActionChart() throws IOException {
        List<Object> list = subAreaService.findSubAreas();
        listToJson(list,null);

        return NONE;
    }


    @Action(value = "subAreaAction_findByPage")
    public String findByPage() throws IOException {
        Pageable pageable = new PageRequest(page-1, rows);
        Page<SubArea> page = subAreaService.findByPage(pageable);
        parentPage(page,new String[]{"subareas","fixedArea"});
        return NONE;

    }


}
