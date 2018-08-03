package com.itheima.bos.service.impl;

import com.itheima.bos.dao.AreaDao;
import com.itheima.bos.service.AreaService;
import com.itheima.domain.Area;
import com.itheima.domain.Standard;
import com.itheima.utils.PinYin4jUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;


    @Override
    public void importfile(File areaFile) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(areaFile));
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
        ArrayList<Area> list = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            String id = row.getCell(0).getStringCellValue();
            String province = row.getCell(1).getStringCellValue();
            String city = row.getCell(2).getStringCellValue();
            String district = row.getCell(3).getStringCellValue();
            String postcode = row.getCell(4).getStringCellValue();

            //简码
            String province2 = province.substring(0, province.length() - 1);
            String city2 = city.substring(0, city.length() - 1);
            String distinct2 = district.substring(0, district.length() - 1);

            String[] headByString = PinYin4jUtils.getHeadByString(province2 + city2 + distinct2);
            String shortcode = PinYin4jUtils.stringArrayToString(headByString, "");

            //城市编码
            String citycode = PinYin4jUtils.hanziToPinyin(city2, "");

            Area area = new Area(id, province, city, district, postcode, citycode, shortcode);
            list.add(area);
        }
        areaDao.save(list);
        hssfWorkbook.close();
    }

    @Override
    public Page<Area> findByPage(Pageable pageable) {
        Page<Area> page2 = areaDao.findAll(pageable);
        return page2;
    }

    @Override
    public List<Area> findAll() {
        List<Area> list = areaDao.findAll();
        return list;
    }

    @Override
    public List<Area> findByQ(String q) {
        List<Area> list= areaDao.findByQ(q);
        return list;
    }
}
