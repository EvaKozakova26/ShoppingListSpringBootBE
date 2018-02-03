package cz.uhk.ppro.demo.Repository;

import cz.uhk.ppro.demo.Model.Demo;

import java.util.Optional;

public interface DemoRepository {
    Optional<Demo> findById(int id);
    void save(Demo demo);
}
