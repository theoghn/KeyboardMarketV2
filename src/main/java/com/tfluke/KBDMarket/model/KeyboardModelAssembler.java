package com.tfluke.KBDMarket.model;

import com.tfluke.KBDMarket.controller.KeyboardController;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class KeyboardModelAssembler extends RepresentationModelAssemblerSupport<Keyboard,KeyboardModel> {
    public KeyboardModelAssembler() {
        super(KeyboardController.class, KeyboardModel.class);
    }
    @Override
    public KeyboardModel toModel(Keyboard entity) {
        KeyboardModel model = new KeyboardModel();
        // Both CustomerModel and Customer have the same property names. So copy the values from the Entity to the Model
        BeanUtils.copyProperties(entity, model);
        return model;
    }
}
