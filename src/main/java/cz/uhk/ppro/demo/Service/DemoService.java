package cz.uhk.ppro.demo.Service;

import cz.uhk.ppro.demo.Model.Demo;
import cz.uhk.ppro.demo.Repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;

@Service
public class DemoService {

    private DemoRepository demoRepository;

    @Autowired
    public DemoService(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    @Transactional
    public void saveAdvert(Demo demo) {
        demo.setTimestamp(new Timestamp(System.currentTimeMillis()));
        demoRepository.save(demo);
    }

}
