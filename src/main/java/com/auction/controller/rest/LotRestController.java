package com.auction.controller.rest;

import com.auction.model.entity.Lot;
import com.auction.model.form.LotCreateForm;
import com.auction.model.form.search.LotRestCreateForm;
import com.auction.service.LotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helga on 01.11.17.
 */

@RestController
public class LotRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LotRestController.class);

    //add two methods: search by id and search all
    //response - json

    @Autowired
    private LotService lotService;

    /**
     * This method get lot by id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "rest/lot/{id}", method = RequestMethod.GET)
    public ResponseEntity<Lot> getLotById(@PathVariable("id") Long id) {
        Lot lot = lotService.getLotById(id);
        if (lot == null) {
            LOGGER.debug("error.rest.lot.get.id");
            return new ResponseEntity<Lot>(HttpStatus.NO_CONTENT);
        }
        LOGGER.info("message.rest.lot.get.id");
        return new ResponseEntity<Lot>(lot, HttpStatus.OK);
    }

    /**
     * This method for get search page
     *
     * @param pageId
     * @param name
     * @return
     */
    @RequestMapping(value = "/lots/{pageId}/{name}", method = RequestMethod.GET)
    public ModelAndView searchLots(@PathVariable int pageId, @PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView();
        List<Lot> lots = lotService.getLotByName(name);
        modelAndView.addObject("lots", lots);
        modelAndView.setViewName("lots/lots");
        return modelAndView;
    }

    /**
     * This method for search lot by name
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/lots/rest/search", method = RequestMethod.GET)
    public ResponseEntity<List<LotRestCreateForm>> searchByName(@RequestParam("name") String name) {
        //
        List<LotCreateForm> lots = lotService.searchLotByName(name);

        List<LotRestCreateForm> lotRestCreateForms = new ArrayList<>(lots.size());

        for (LotCreateForm lot : lots) {
            LotRestCreateForm lotRestCreateForm = new LotRestCreateForm();
            lotRestCreateForm.setId(lot.getId());
            lotRestCreateForm.setName(lot.getName());
            lotRestCreateForm.setDescription(lot.getBayoutPrice().toString());
            lotRestCreateForm.setOwner(lot.getUserId());
            lotRestCreateForms.add(lotRestCreateForm);
        }

        if (lots.isEmpty()) {
            LOGGER.debug("error.rest.lots.get.all");
            return new ResponseEntity<List<LotRestCreateForm>>(lotRestCreateForms, HttpStatus.OK);
        }
        LOGGER.info("message.rest.lots.get.all");
        return new ResponseEntity<List<LotRestCreateForm>>(lotRestCreateForms, HttpStatus.OK);
    }
}
