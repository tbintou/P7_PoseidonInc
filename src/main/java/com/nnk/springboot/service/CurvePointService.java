package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;

import java.util.List;

public interface CurvePointService {
    void addCurvePoint(CurvePoint curvePoint);

    Boolean updateCurvePoint(int id, CurvePoint curvePoint);

    List<CurvePoint> findAll();

    CurvePoint fidById(int id);

    void deleteById(int id);
}
