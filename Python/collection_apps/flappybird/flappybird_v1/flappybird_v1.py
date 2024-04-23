import pygame
import sys
import random
import time
 
pygame.init()

width, height = 1200, 720

screen = pygame.display.set_mode((width, height))
pygame.display.set_caption("Flappy Bird - Devinitive Edition")

white = (255, 255, 255)
blue = (0, 0, 255)
green = (0, 255, 0)

bird_width, bird_height = 50, 50
bird_x = width // 4
bird_y = height // 2
bird_velocity = 0

gravity = 1

jump_strength = 15

obstacle_width, obstacle_height = 50, random.randint(150, 400)
obstacle_x = width
obstacle_gap = 200
obstacle_speed = 5

clock = pygame.time.Clock()

def draw_bird(x, y):
    pygame.draw.rect(screen, blue, (x, y, bird_width, bird_height))

def draw_obstacle(x, height):
    pygame.draw.rect(screen, green, (x, 0, obstacle_width, height))
    pygame.draw.rect(screen, green, (x, height + obstacle_gap, obstacle_width, height + obstacle_gap + 600))

while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_SPACE:
                bird_velocity = -jump_strength

    bird_velocity += gravity
    bird_y += bird_velocity

    obstacle_x -= obstacle_speed
    if obstacle_x < -obstacle_width:
        obstacle_x = width
        obstacle_height = random.randint(150, 400)
    if bird_x < obstacle_x + obstacle_width < bird_x + bird_width and \
       not (bird_y > obstacle_height and bird_y + bird_height < obstacle_height + obstacle_gap):
        print("Kollision! Spiel vorbei.")
        time.sleep(3)
        pygame.quit()
        sys.exit()

    screen.fill(white)

    draw_obstacle(obstacle_x, obstacle_height)
    draw_bird(bird_x, bird_y)

    pygame.display.flip()

    clock.tick(30)
