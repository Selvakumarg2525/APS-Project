

import com.Donation.FoodDonation.model.Recipient;
import com.Donation.FoodDonation.repository.RecipientRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class RController {
    @Autowired
    private RecipientRepository recipientRepository;

    @GetMapping("/recipient/login")
    public String showRecipientLoginForm() {
        return "recipient-login";
    }
    @GetMapping("/recipient/logout")
    public String showRecipientLogoutForm() {
        return "recipient-login";
    }

    @PostMapping("/recipient/login")
    public String handleRecipientLogin(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        Recipient recipient = recipientRepository.findByEmailAndPassword(email, password);
        if (recipient != null) {
            // Pass the recipientId when logging in
            session.setAttribute("recipientId",recipient.getRecipientId());
            session.setAttribute("recipientName",recipient.getName());
            return "redirect:/recipient/dashboard";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "recipient-login";
        }
}
