package org.example.academic_supervision.Controller;

public class AdvisersController {
    private final static Logger logger = LoggerFactory.getLogger(AdvisersController.class);
    private final IAdvisersService advisersService;

    public AdvisersController(IAdvisersService advisersService) {
        this.advisersService = advisersService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdvisersDTO>> getAllAdvisers() {
        return new ResponseEntity<>(advisersService.getAllAdvisers(), HttpStatus.OK);
    }
}
