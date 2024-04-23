import pygame
import sys
import random
#import time
import os

pygame.init()

width, height = 1200, 720

screen = pygame.display.set_mode((width, height))
pygame.display.set_caption("Flappy Bird - Definitive Edition")
pygame.mixer.init()
music_folder = os.path.abspath("Python/collection_apps/flappybird/flappybird_v3/music")
music_files = [
    os.path.join(music_folder, "song1.mp3"),
    os.path.join(music_folder, "song2.mp3"),
    os.path.join(music_folder, "fail.mp3"),
    os.path.join(music_folder, "sad.mp3"),
    os.path.join(music_folder, "oh_yeah.mp3"),
    os.path.join(music_folder, "lobby.mp3")
]

white = (255, 255, 255)
blue = (0, 0, 255)
green = (0, 255, 0)
black = (0, 0, 0)

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

FONT_SIZE = 36

def start_screen():
    screen.fill(black)
    font = pygame.font.Font(None, FONT_SIZE)
    title_text = font.render("Flappy Bird - Definitive Edition", True, white)
    instruction_text = font.render("Press SPACE to start", True, white)
    highscore_text = font.render(f"Highscore: {highscore}", True, white)

    screen.blit(title_text, (width // 4, height // 4))
    screen.blit(instruction_text, (width // 4, height // 2))
    screen.blit(highscore_text, (width // 4, height // 1.5))

    pygame.display.flip()

background_image = pygame.image.load(os.path.join("Python", "collection_apps", "flappybird", "flappybird_v3", "files", "background.png"))

def draw_background():
    screen.blit(background_image, (0, 0))

def draw_score(counter):
    font = pygame.font.Font(None, FONT_SIZE)
    score_text = font.render(f"Score: {counter}", True, white)
    screen.blit(score_text, (width - 150, 20))

def game_over_screen(counter):
    screen.fill(black)
    font = pygame.font.Font(None, FONT_SIZE)
    game_over_text = font.render("Game Over", True, white)
    score_text = font.render(f"Score: {counter}", True, white)
    instruction_text = font.render("Press SPACE to restart", True, white)

    screen.blit(game_over_text, (width // 4, height // 4))
    screen.blit(score_text, (width // 4, height // 2))
    screen.blit(instruction_text, (width // 4, height // 1.5))

    pygame.display.flip()

def draw_obstacle(x, height):
    pygame.draw.rect(screen, green, (x, 0, obstacle_width, height))
    pygame.draw.rect(screen, green, (x, height + obstacle_gap, obstacle_width, height + obstacle_gap + 600))

def draw_bird(x, y):
    pygame.draw.rect(screen, blue, (x, y, bird_width, bird_height))


def flappy_bird_game():
    global bird_y, bird_velocity, obstacle_x, obstacle_height, counter, highscore, game_active
    game_active = False
    obstacle_speed = 5
    obstacles_passed = 0
    #Lobby
    selected_music = pygame.mixer.music.load(music_files[5])
    pygame.mixer.music.play(-1)

    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            elif event.type == pygame.KEYDOWN:
                if event.key == pygame.K_SPACE:
                    bird_velocity = -jump_strength
                    bird_y -= jump_strength
                    if not game_active:
                        game_active = True
                        bird_y = height // 2
                        bird_velocity = 0
                        obstacle_x = width
                        counter = 0
                        #ingame - normal
                        if counter < 7:
                            selected_music = pygame.mixer.music.load(music_files[0])
                            pygame.mixer.music.play(-1)
                        
                        
        if game_active:
            bird_velocity += gravity
            bird_y += bird_velocity

            if bird_y > height - bird_height or bird_y < 0:
                game_active = False
                obstacle_speed = 5
                #sad
                selected_music = pygame.mixer.music.load(music_files[3])
                pygame.mixer.music.play()
                                        
                #new highscore
                if counter > highscore:
                    highscore = counter
                    selected_music = pygame.mixer.music.load(music_files[4])
                    pygame.mixer.music.play()
                    
                    
            obstacle_x -= obstacle_speed
            if obstacle_x < -obstacle_width:
                obstacle_x = width
                obstacle_height = random.randint(150, 400)
                counter += 1
                obstacles_passed +=1

                if obstacles_passed % 3 == 0:
                    obstacle_speed +=1

                #ingame - high 
                if counter == 7:               
                    selected_music = pygame.mixer.music.load(music_files[1])
                    pygame.mixer.music.play(-1)

            if bird_x < obstacle_x + obstacle_width < bird_x + bird_width and \
               not (bird_y > obstacle_height and bird_y + bird_height < obstacle_height + obstacle_gap):
                game_active = False
                obstacle_speed = 5
                #sad
                selected_music = pygame.mixer.music.load(music_files[2])
                pygame.mixer.music.play()
                #new highscore
                if counter > highscore:
                    highscore = counter
                    #pygame.mixer.music.stop()
                    selected_music = pygame.mixer.music.load(music_files[4])
                    pygame.mixer.music.play()

            screen.fill(black)
            draw_background()
            draw_obstacle(obstacle_x, obstacle_height)
            draw_bird(bird_x, bird_y)
            draw_score(counter)

        else:
            start_screen()

        pygame.display.flip()
        clock.tick(30)

counter = 0
highscore = 0

flappy_bird_game()
