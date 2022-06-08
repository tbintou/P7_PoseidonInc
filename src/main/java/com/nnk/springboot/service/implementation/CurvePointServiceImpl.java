package com.nnk.springboot.service.implementation;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurvePointService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CurvePointServiceImpl implements CurvePointService {

    private final CurvePointRepository curvePointRepository;

    @Autowired
    public CurvePointServiceImpl(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }

    @Override
    public void addCurvePoint(CurvePoint curvePoint) {
        curvePointRepository.save(curvePoint);
        log.info("New curve point " + curvePoint + " is created !");
    }

    @Override
    public Boolean updateCurvePoint(int id, CurvePoint curvePoint) {

        Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
        if (curvePointList.isPresent()){
            CurvePoint newCurvePoint = curvePointList.get();
            newCurvePoint.setCurvePointId(curvePoint.getCurvePointId());
            newCurvePoint.setTerm(curvePoint.getTerm());
            newCurvePoint.setValue(curvePoint.getValue());
            curvePointRepository.save(newCurvePoint);
            log.info("Curve point with id " + id + " is updated as " + newCurvePoint);
            return true;
        }
        log.info("Failed to update curve point with id " + id + " as" + curvePoint);
        return false;
    }

    @Override
    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }

    @Override
    public CurvePoint fidById(int id) {
        Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
        if (curvePointList.isPresent()){
            log.info("the id of curve point is " + id);
            return curvePointList.get();
        } else {
            log.info("Failed to find curve point with id " + id);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
        if (curvePointList.isPresent()){
            curvePointRepository.delete(curvePointList.get());
            log.info("Curve point with this id " + id + " has been deleted !");
        }
            log.info("Failed to delete curve point with id " + id);
    }
}
