package fr.esgi.avis.controller.Avatar.rest;

import fr.esgi.avis.controller.Avatar.AvatarController;
import fr.esgi.avis.controller.Avatar.AvatarDtoMapper;
import fr.esgi.avis.controller.Avatar.dto.AvatarDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller for avatar
 */
@RestController
@RequestMapping("/api/avatars")
@RequiredArgsConstructor
public class AvatarRestController {

    private final AvatarController avatarController;

    @PostMapping
    public ResponseEntity<AvatarDTO> createAvatar(@RequestBody AvatarDTO avatarDTO) {
        AvatarDTO createdAvatarDto = avatarController.createAvatar(avatarDTO);
        return ResponseEntity.ok(createdAvatarDto);
    }

    /**
     * Get an avatar by id
     * @param id
     * @return the avatar
     */
    @GetMapping("/{id}")
    public ResponseEntity<AvatarDTO> getAvatarById(@PathVariable Long id) {
        return avatarController.getAvatarById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
                // TODO : throw exception if not found
    }

    /**
     * Delete an avatar by id
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvatarById(@PathVariable Long id) {
        avatarController.deleteAvatarById(id);
        return ResponseEntity.noContent().build();
    }


}
