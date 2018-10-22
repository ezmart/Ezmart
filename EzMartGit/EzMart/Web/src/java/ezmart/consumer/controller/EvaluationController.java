package ezmart.consumer.controller;

import ezmart.model.entity.Avaliation;
import ezmart.model.entity.Consumer;
import ezmart.model.entity.Establishment;
import ezmart.model.entity.User;
import ezmart.model.service.AvaliationService;
import ezmart.model.service.ConsumerService;
import ezmart.model.service.EstablishmentService;
import ezmart.model.util.SystemConstant;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EvaluationController {

    @RequestMapping(value = "/marketEvaluation", method = RequestMethod.GET)
    public ModelAndView getFormMarketEvaluation() {
        ModelAndView mv = new ModelAndView("evaluation/market_evaluation");
        try {
            EstablishmentService service = new EstablishmentService();
            Map<Long, Object> criteria = new HashMap<>();
            //criteria.put(UserCriteria.USER_TYPE_EQ, SystemConstant.USER.TYPE.ESTABLISHMENT);
            List<Establishment> establishmentList = service.readByCriteria(criteria, null, null);

            mv.addObject("establishmentList", establishmentList);

        } catch (Exception e) {

        }
        return mv;
    }

    @RequestMapping(value = "/marketEvaluation", method = RequestMethod.POST)
    public ModelAndView setMarketEvaluation(Long establishmentId, Integer satisfaction, Integer priceProduct,
            Integer prodDiversity, Integer employees, Integer ambience, String commentary, HttpSession session) {
        ModelAndView mv = new ModelAndView("message/message_evaluation_success");
        Object auxSession = session.getAttribute("userLogged");
        User user = (Consumer) auxSession;
        try {
            AvaliationService avaliationService = new AvaliationService();

            Map<String, Object> fields = new HashMap<>();
            fields.put("establishmentId", establishmentId);
            fields.put("satisfaction", satisfaction);
            fields.put("priceProduct", priceProduct);
            fields.put("prodDiversity", prodDiversity);
            fields.put("employees", employees);
            fields.put("ambience", ambience);
            fields.put("commentary", commentary);

            fields.put("validationType", SystemConstant.VALIDATION.EVALUATION.REGISTER_EVALUATION);

            Map<String, String> errors = avaliationService.validate(fields);

            if (errors.isEmpty()) {
                Avaliation avaliation = new Avaliation();

                EstablishmentService establishmentService = new EstablishmentService();
                Establishment establishment = establishmentService.readById(establishmentId);
                avaliation.setEstablishment(establishment);

                ConsumerService consumerService = new ConsumerService();
                Consumer consumer = consumerService.readById(user.getId());
                avaliation.setConsumer(consumer);

                avaliation.setSatisfaction(satisfaction);
                avaliation.setProductPrice(priceProduct);
                avaliation.setDiversity(prodDiversity);
                avaliation.setEmployees(employees);
                avaliation.setAmbience(ambience);
                avaliation.setCommentary(commentary);

                //Paga a data atual do sistema
                Date dateAvaliation = new Date(System.currentTimeMillis());
                avaliation.setDateAvaliation(dateAvaliation);

                avaliationService.create(avaliation);
            } else {
                mv = new ModelAndView("evaluation/market_evaluation");

                //Faz com que não se perca o que já foi inserido no formulário
                if (establishmentId != null) {
                    mv.addObject("establishmentId", establishmentId);
                }
                if (satisfaction != null) {
                    mv.addObject("satisfaction", satisfaction);
                }
                if (priceProduct != null) {
                    mv.addObject("priceProduct", priceProduct);
                }
                if (prodDiversity != null) {
                    mv.addObject("prodDiversity", prodDiversity);
                }
                if (employees != null) {
                    mv.addObject("employees", employees);
                }
                if (ambience != null) {
                    mv.addObject("ambience", ambience);
                }
                if (commentary != null && !commentary.isEmpty()) {
                    mv.addObject("commentary", commentary);
                }

                EstablishmentService service = new EstablishmentService();
                Map<Long, Object> criteria = new HashMap<>();
                //criteria.put(UserCriteria.USER_TYPE_EQ, SystemConstant.USER.TYPE.ESTABLISHMENT);
                List<Establishment> establishmentList = service.readByCriteria(criteria, null, null);
                mv.addObject("establishmentList", establishmentList);
                
                //Caso haja erros, será mostrado
                mv.addObject("errors", errors);
            }
        } catch (Exception e) {
            mv = new ModelAndView("message/message_system_error");
        }
        return mv;
    }
}
