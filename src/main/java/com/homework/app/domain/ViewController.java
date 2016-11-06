package com.homework.app.domain;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.homework.app.core.model.City;
import com.homework.app.core.model.Customer;
import com.homework.app.core.service.ICityService;
import com.homework.app.core.service.ICustomerService;

@ViewScoped
@Component
@ManagedBean
public class ViewController {

    private static Logger logger = Logger.getLogger(ViewController.class.getName());

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ICityService cityService;

    private List<Customer> customers;

    private List<City> cities;

    private List<City> citiesWithCustomers;

    private Long selectedCity;


    public Long getSelectedCity() {
        return selectedCity;
    }


    public void setSelectedCity(Long selectedCity) {
        this.selectedCity = selectedCity;
    }


    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }


    public List<City> getCitiesWithCustomers() {
        if (citiesWithCustomers == null) {
            citiesWithCustomers = cityService.listCitiesWithCustomers();
        }
        return citiesWithCustomers;
    }


    public void setCitiesWithCustomers(List<City> citiesWithCustomers) {
        this.citiesWithCustomers = citiesWithCustomers;
    }


    public List<City> getCities() {
        if (cities == null) {
            try {
                cities = cityService.list();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                showMessage("exception", "problem with cities");
                cities = new ArrayList<>();
            }
        }
        return cities;
    }


    public void setCities(List<City> cities) {
        this.cities = cities;
    }


    public synchronized void removeCustomerRow(Customer customer) {
        try {
            customerService.delete(customer);
            customers.remove(customer);
            citiesWithCustomers.stream().forEach(city -> city.getCustomers().removeIf(c -> customer.getId().equals(c.getId())));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            showMessage("exception", "problem with removing customer");
        }
    }


    public List<Customer> getCustomers() {
        if (customers == null) {
            try {
                customers = customerService.list();

            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                showMessage("exception", "problem with customers");
                customers = new ArrayList<Customer>();
            }
        }
        return customers;
    }


    public void onRowCancel(RowEditEvent event) {
        showMessage("Edit Cancelled", "");
    }


    public synchronized void onRowEdit(RowEditEvent event) {
        try {
            Customer customer = (Customer) event.getObject();
            if (logger.isInfoEnabled()) {
                logger.info("row edit: " + customer + " selectedCity: " + selectedCity);
            }

            customer.setCity(cities.stream().filter(c -> c.getId().equals(selectedCity)).findAny().get());
            customerService.update(customer);

            customers = null;
            citiesWithCustomers = null;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            showMessage("exception", "problem with editing customer row");
        }
        showMessage("Edited", "");
    }


    public synchronized void addCustomerRow(ActionEvent event) {
        try {
            Customer customer = new Customer();
            customers.add(customer);
            customerService.save(customer);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            showMessage("exception", "problem with adding customer row");
        }
    }


    public void showMessage(String messageHeader, String messageText) {
        FacesMessage msg = new FacesMessage(messageHeader, messageText);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
