import pygame
import sys

# Initialisierung von Pygame
pygame.init()

# Fenstergröße
width, height = 1200, 720

# Erstellen des Fensters
screen = pygame.display.set_mode((width, height))
pygame.display.set_caption("Flappy Bird Sprungbewegung")

# Farben
white = (255, 255, 255)
blue = (0, 0, 255)

# Vogel
bird_width, bird_height = 50, 50
bird_x = width // 4
bird_y = height // 2
bird_velocity = 0

# Schwerkraft
gravity = 1

# Sprungkraft
jump_strength = 15

clock = pygame.time.Clock()

while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_SPACE:
                bird_velocity = -jump_strength

    # Bewegung des Vogels
    bird_velocity += gravity
    bird_y += bird_velocity

    # Zeichnen
    screen.fill(white)
    pygame.draw.rect(screen, blue, (bird_x, bird_y, bird_width, bird_height))

    pygame.display.flip()

    # Begrenzung der Bildwiederholungsrate
    clock.tick(30)
