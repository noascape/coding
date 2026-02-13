--------------------------------------------------------------------------------
-- MapName: Gut gegen Böse
-- Author : noacape
--------------------------------------------------------------------------------

-- Fallback für Umlaute, falls InitUmlautFix noch nicht gelaufen ist
Umlaute = Umlaute or function(t) return t end

-- Kleiner Helfer: NPC-Marker einschalten, wenn die Entität existiert
local function EnsureNpcMarkerOn(_name)
    if IsExisting(_name) then
        EnableNpcMarker(GetEntityId(_name))
    end
end

local function ExploreAreaForPlayer(_pid, _x, _y, _range)
    -- Unsichtbare Script-Entity für diesen Spieler erstellen
    local id = Logic.CreateEntity(Entities.XD_ScriptEntity, _x, _y, 0, _pid)
    -- Sichtweite / Aufdeckradius setzen
    Logic.SetEntityExplorationRange(id, _range)
end

-- Score-Wrapper: verhindert Fehler beim Beenden, wenn Score.Player nicht initialisiert ist
local function InitScoreSafeWrapper()
    if not Score or not Score.GetPlayerScore or Score.GetPlayerScore_Orig then
        return
    end

    -- Originalfunktion sichern
    Score.GetPlayerScore_Orig = Score.GetPlayerScore

    function Score.GetPlayerScore(_PlayerID, _ScoreType)
        if not Score.Player
           or not Score.Player[_PlayerID]
           or Score.Player[_PlayerID][_ScoreType] == nil then
            return 0
        end
        return Score.GetPlayerScore_Orig(_PlayerID, _ScoreType)
    end
end

local function ExploreAreaForPlayer(_pid, _x, _y, _range)
    local id = Logic.CreateEntity(Entities.XD_ScriptEntity, _x, _y, 0, _pid)
    Logic.SetEntityExplorationRange(id, _range)
end

-- An einer Position für beide KI-Verbündeten (3 und 4) aufdecken
local function ExploreNpcForBothPlayers(_name, _range)
    if not IsExisting(_name) then
        return
    end
    local pos = GetPosition(_name)
    for pid = 1, 2 do
        ExploreAreaForPlayer(pid, pos.X, pos.Y, _range)
    end
end

--------------------------------------------------------------------------------
-- Game start
--------------------------------------------------------------------------------

function GameCallback_OnGameStart()

    gvMission = gvMission or {}
    gvMission.PlayerID = GUI.GetPlayerID() or 1

    -- Include global tool script functions 
    Script.Load(Folders.MapTools.."Ai\\Support.lua")
    Script.Load(Folders.MapTools.."Main.lua")

    -- Editor- / Komfortfunktionen
    IncludeGlobals("MapEditorTools")
    IncludeGlobals("Comfort")

    -- Briefing- und NPC-Bibliotheken
    IncludeGlobals("Briefing")
    IncludeGlobals("NPC")

    -- Umlaut-Fix initialisieren (nach NPC-Import!)
    InitUmlautFix()

    Script.Load("Data\\Script\\MapTools\\Counter.lua")
    Script.Load("Data\\Script\\MapTools\\MultiPlayer\\MultiplayerTools.lua")
    Script.Load("Data\\Script\\MapTools\\Tools.lua")
    Script.Load("Data\\Script\\MapTools\\WeatherSets.lua")

    -- Score-Wrapper aktivieren
    InitScoreSafeWrapper()

    -- Init local map stuff
    Mission_InitWeatherGfxSets()
    InitWeather()
    Mission_InitGroups()
    Mission_InitLocalResources()

    -- Init global MP stuff
    MultiplayerTools.InitCameraPositionsForPlayers()
    MultiplayerTools.SetUpGameLogicOnMPGameConfig()
    MultiplayerTools.GiveBuyableHerosToHumanPlayer(2) -- 2 Helden pro Spieler

    -- Alarmlimit
    EnableAlarmLimit()
    -- Überstundenlimit
    EnableOvertimeLimit()

    if XNetwork.Manager_DoesExist() == 0 then
        for i = 1, 2 do
            MultiplayerTools.DeleteFastGameStuff(i)
        end
        local PlayerID = GUI.GetPlayerID()
        Logic.PlayerSetIsHumanFlag(PlayerID, 1)
        Logic.PlayerSetGameStateToPlaying(PlayerID)
    end

    -- Musik
    LocalMusic.UseSet = HIGHLANDMUSIC

    -- Standardsiegbedingung
    MapEditor_SetupDestroyVictoryCondition(3)
    StartSimpleJob("DefeatJobP1")
    StartSimpleJob("DefeatJobP2")

    --------------------------------------------------------------------
    -- KI-Spieler aufsetzen
    --------------------------------------------------------------------

    -- Standard-Shared-Exploration (jede Seite mit der eigenen Haupt-KI)
    ActivateShareExploration(1, 3, true)
    ActivateShareExploration(2, 4, true)

    -- Spieler 3 (Varg)
    local aiID       = 3
    local strength   = 3
    local range      = 10000
    local techlevel  = 2
    local position   = "P3_AI_HQ"
    local aggressiveness = 3
    local peacetime  = 0

    MapEditor_SetupAI(aiID, strength, range, techlevel, position, aggressiveness, peacetime)
    SetupPlayerAi(aiID, { extracting = 1, repairing = 1 })
    SetPlayerName(aiID, "Varg")

    -- Spieler 4 (Dario)
    aiID       = 4
    strength   = 3
    range      = 10000
    techlevel  = 3
    position   = "P4_AI_HQ"
    aggressiveness = 3
    peacetime  = 0

    MapEditor_SetupAI(aiID, strength, range, techlevel, position, aggressiveness, peacetime)
    SetupPlayerAi(aiID, { extracting = 1, repairing = 1 })
    SetPlayerName(aiID, "Dario")

    -- Spieler 5 (Wismar)
    aiID       = 5
    strength   = 3
    range      = 10000
    techlevel  = 3
    position   = "P5_AI_HQ"
    aggressiveness = 3
    peacetime  = 0

    MapEditor_SetupAI(aiID, strength, range, techlevel, position, aggressiveness, peacetime)
    SetupPlayerAi(aiID, { extracting = 1, repairing = 1 })
    SetPlayerName(aiID, "Wismar")

    -- Spieler 6 (Bergbewohner)
    aiID       = 6
    strength   = 3
    range      = 10000
    techlevel  = 3
    position   = "P6_AI_HQ"
    aggressiveness = 3
    peacetime  = 360

    MapEditor_SetupAI(aiID, strength, range, techlevel, position, aggressiveness, peacetime)
    SetupPlayerAi(aiID, { extracting = 1, repairing = 1 })
    SetPlayerName(aiID, "Bergbewohner")

    -- Spieler 7 (Celle)
    aiID       = 7
    strength   = 3
    range      = 10000
    techlevel  = 3
    position   = "P7_AI_HQ"
    aggressiveness = 3
    peacetime  = 0

    MapEditor_SetupAI(aiID, strength, range, techlevel, position, aggressiveness, peacetime)
    SetupPlayerAi(aiID, { extracting = 1, repairing = 1 })
    SetPlayerName(aiID, "Celle")
    Display.SetPlayerColorMapping(7, ROBBERS_COLOR)

    -- Spieler 8 (Likirchen)
    aiID       = 8
    strength   = 3
    range      = 10000
    techlevel  = 2
    position   = "P8_AI_HQ"
    aggressiveness = 3
    peacetime  = 360

    MapEditor_SetupAI(aiID, strength, range, techlevel, position, aggressiveness, peacetime)
    SetupPlayerAi(aiID, { extracting = 1, repairing = 1 })
    SetPlayerName(aiID, "Likirchen")

    --------------------------------------------------------------------
    -- Diplomatie Grundzustand
    --------------------------------------------------------------------
    SetFriendly(1, 3)
    SetHostile(2, 3)
    SetHostile(4, 3)

    SetNeutral(1, 5)
    SetNeutral(1, 6)
    SetNeutral(1, 7)
    SetNeutral(1, 8)

    SetNeutral(2, 5)
    SetNeutral(2, 6)
    SetNeutral(2, 7)
    SetNeutral(2, 8)

    SetFriendly(2, 4)
    SetHostile(2, 1)
    SetHostile(1, 4)

    SetHostile(5, 7)

    FirstMapAction()
end

--------------------------------------------------------------------------------
-- Wetter, Gruppen, Technologien, Ressourcen
--------------------------------------------------------------------------------

function Mission_InitWeatherGfxSets()
    SetupEvelanceWeatherGfxSet()
end

function InitWeather()
    AddPeriodicSummer(600)
    AddPeriodicRain(20)
    AddPeriodicWinter(200)
    AddPeriodicRain(20)
    AddPeriodicSummer(600)
    AddPeriodicRain(40)
    AddPeriodicWinter(400)
    AddPeriodicRain(20)
    AddPeriodicWinter(120)
end

function Mission_InitGroups()
    -- hier ggf. Gruppen aufbauen
end

function Mission_InitTechnologies()
    -- keine Einschränkungen
end

function Mission_InitLocalResources()
    local InitGoldRaw   = 1000
    local InitClayRaw   = 2000
    local InitWoodRaw   = 2000
    local InitStoneRaw  = 1000
    local InitIronRaw   = 500
    local InitSulfurRaw = 500

    for i = 1, 8 do
        Tools.GiveResouces(i, InitGoldRaw, InitClayRaw, InitWoodRaw,
                           InitStoneRaw, InitIronRaw, InitSulfurRaw)
    end
end

--------------------------------------------------------------------------------
-- Start / Niederlagenbedingungen
--------------------------------------------------------------------------------

function FirstMapAction()

    -- Schutz gegen mehrfachen Aufruf
    if IntroStarted then
        return
    end
    IntroStarted = true

    -- Quests initialisieren
    InitQuestSystem()

    -- Intro-Briefing
    StartIntroBriefing()

    -- NPC-Marker aktivieren
    EnsureNpcMarkerOn("Leader_Wismar")
    EnsureNpcMarkerOn("Leader_Celle")
    EnsureNpcMarkerOn("Leader_Bergbewohner")
    EnsureNpcMarkerOn("Waechter_1")
    EnsureNpcMarkerOn("Waechter_2")
    EnsureNpcMarkerOn("Hauptmann_Likirchen")
    EnsureNpcMarkerOn("Bischof_Likirchen")

    -- Bereiche um wichtige NPCs aufdecken (kleiner Radius)
    ExploreNpcForBothPlayers("Leader_Celle",       15)
    ExploreNpcForBothPlayers("Leader_Wismar",      15)
    ExploreNpcForBothPlayers("Waechter_1",         15)
    ExploreNpcForBothPlayers("Waechter_2",         15)
    

    -- for pid = 1, 2 do
     --   ExploreAreaForPlayer(pid, 35697.5, 37076.1, 15)   -- Celle
      --  ExploreAreaForPlayer(pid, 41059.4, 61382.0, 15)   -- Wismar
       --  ExploreAreaForPlayer(pid, 22347.6, 51611.2, 15)   -- Wächter 1
      --  ExploreAreaForPlayer(pid, 50436.9, 48387.3, 15)   -- Wächter 2
    --end

    -- optional: Minimap-Pulse (aktuell auskommentiert, weil zu lang)
    -- GUI.CreateMinimapPulse(35697.5, 37076.1, 0)   -- Celle
    -- GUI.CreateMinimapPulse(41059.4, 61382.0, 0)   -- Wismar
    -- GUI.CreateMinimapPulse(22347.6, 51611.2, 0)   -- Wächter 1
    -- GUI.CreateMinimapPulse(50436.9, 48387.3, 0)   -- Wächter 2
end

function DefeatJobP1()
    if (Logic.GetNumberOfEntitiesOfTypeOfPlayer(1, Entities.PB_Headquarters1)
        + Logic.GetNumberOfEntitiesOfTypeOfPlayer(1, Entities.PB_Headquarters2)
        + Logic.GetNumberOfEntitiesOfTypeOfPlayer(1, Entities.PB_Headquarters3)) < 1 then
        Logic.PlayerSetGameStateToLost(1)
        return true
    end
end

function DefeatJobP2()
    if (Logic.GetNumberOfEntitiesOfTypeOfPlayer(2, Entities.PB_Headquarters1)
        + Logic.GetNumberOfEntitiesOfTypeOfPlayer(2, Entities.PB_Headquarters2)
        + Logic.GetNumberOfEntitiesOfTypeOfPlayer(2, Entities.PB_Headquarters3)) < 1 then
        Logic.PlayerSetGameStateToLost(2)
        return true
    end
end

--------------------------------------------------------------------------------
-- Alarm- und Überstunden-Limit
--------------------------------------------------------------------------------

function EnableAlarmLimit()
    GUIAction_ActivateAlarmOrig = GUIAction_ActivateAlarm
    GUIAction_ActivateAlarm = function()
        alarmWait = Logic.GetCurrentTurn() + 600
        GUIAction_ActivateAlarmOrig()
    end

    GUIAction_QuitAlarmOrig = GUIAction_QuitAlarm
    GUIAction_QuitAlarm = function()
        local turns = Logic.GetCurrentTurn()
        if turns >= alarmWait then
            GUIAction_QuitAlarmOrig()
        else
            Sound.PlayFeedbackSound(Sounds.VoicesWorker_WORKER_FunnyNo_rnd_10, 0)
            Message("Der Alarm kann erst in " ..
                math.floor((alarmWait - turns) / 10) ..
                " Sekunden wieder aufgehoben werden.")
        end
    end
end

function EnableOvertimeLimit()
    tOvertimes = {}
    GUI.ToggleOvertimeAtBuildingOrig = GUI.ToggleOvertimeAtBuilding
    GUI.ToggleOvertimeAtBuilding = function(_id)
        LimitOvertime(_id, GUI.ToggleOvertimeAtBuildingOrig)
    end

    GUI.ForceSettlerToWorkOrig = GUI.ForceSettlerToWork
    GUI.ForceSettlerToWork = function(_id)
        LimitOvertime(_id, GUI.ToggleOvertimeAtBuildingOrig)
    end

    LimitOvertime = function(_id, _func)
        local knowntime = tOvertimes[_id]
        local turns     = Logic.GetCurrentTurn()
        if not knowntime or turns > knowntime then
            _func(_id)
            tOvertimes[_id] = turns + 600
        else
            Sound.PlayFeedbackSound(Sounds.VoicesWorker_WORKER_FunnyNo_rnd_10, 0)
            Message("Die Überstunden dauern noch mindestens " ..
                math.floor((knowntime - turns) / 10) .. " Sekunden.")
        end
    end
end

function ActivateShareExploration(_player1, _player2, _both)
    assert(type(_player1) == "number" and type(_player2) == "number"
        and _player1 <= 8 and _player2 <= 8 and _player1 >= 1 and _player2 >= 1)

    if _both == false then
        Logic.SetShareExplorationWithPlayerFlag(_player1, _player2, 1)
    else
        Logic.SetShareExplorationWithPlayerFlag(_player1, _player2, 1)
        Logic.SetShareExplorationWithPlayerFlag(_player2, _player1, 1)
    end
end

--------------------------------------------------------------------------------
-- Umlaut-Fix + Quest-Helfer
--------------------------------------------------------------------------------

InitUmlautFix_Done = InitUmlautFix_Done or false

function InitUmlautFix()
    if InitUmlautFix_Done then
        return
    end
    InitUmlautFix_Done = true

    -- Original-NPC-Funktion sichern
    CreateNPCOrig = CreateNPC

    BugUmlaut = function(_text)
        local texttype = type(_text)
        if texttype == "string" then
            _text = string.gsub(_text, "ä", "ae")
            _text = string.gsub(_text, "ö", "oe")
            _text = string.gsub(_text, "ü", "ue")
            _text = string.gsub(_text, "ß", "ss")
            _text = string.gsub(_text, "Ä", "Ae")
            _text = string.gsub(_text, "Ö", "Oe")
            _text = string.gsub(_text, "Ü", "Ue")
            return _text
        elseif texttype == "table" then
            for k, v in pairs(_text) do
                _text[k] = Umlaute(v)
            end
            return _text
        else
            return _text
        end
    end

    Umlaute = function(_text)
        local texttype = type(_text)
        if texttype == "string" then
            _text = string.gsub(_text, "ä", "\195\164")
            _text = string.gsub(_text, "ö", "\195\182")
            _text = string.gsub(_text, "ü", "\195\188")
            _text = string.gsub(_text, "ß", "\195\159")
            _text = string.gsub(_text, "Ä", "\195\132")
            _text = string.gsub(_text, "Ö", "\195\150")
            _text = string.gsub(_text, "Ü", "\195\156")
            return _text
        elseif texttype == "table" then
            for k, v in pairs(_text) do
                _text[k] = Umlaute(v)
            end
            return _text
        else
            return _text
        end
    end

    -- Alle NPCs beim Anlegen durch Umlaute schicken
    CreateNPC = function(_npc)
        CreateNPCOrig(Umlaute(_npc))
    end
end

-- Quest-Helfer mit Umlaute-Unterstützung
function AddQuestUTF8(pid, id, qtype, title, text, visible)
    Logic.AddQuest(
        pid,
        id,
        qtype,
        Umlaute(title),
        Umlaute(text),
        visible
    )
end

--------------------------------------------------------------------------------
-- Allgemeine Dialog-Helfer + Intro
--------------------------------------------------------------------------------

-- Einfache Ein-Seiten-Briefings / Dialoge im großen Fenster
local function SimpleBriefing(_entityOrPos, _title, _text)
    local pos
    if type(_entityOrPos) == "string" then
        pos = GetPosition(_entityOrPos)
    else
        pos = _entityOrPos
    end

    local briefing = {}
    BRIEFING_TIMER_PER_CHAR = 1.0

    briefing[1] = {}
    briefing[1].title        = _title
    briefing[1].text         = _text
    briefing[1].position     = pos
    briefing[1].dialogCamera = true

    StartBriefing(Umlaute(briefing))
end

-- Für NPC-Dialoge
function NpcSayOnePage_BigDialog(_npcEntityName, _title, _text)
    SimpleBriefing(_npcEntityName, _title, _text)
end

-- Intro-Briefing: Kamera auf Scriptpunkt "Briefing"
function StartIntroBriefing()
    local b = {}
    b.restoreCamera = true

    local page = 0
    page = page + 1
    b[page] = {}
    b[page].title    = "Gut gegen Böse"
    b[page].text     =
        "Zwei Reiche, vier Nachbarn und viele unentschlossene Städte. " ..
        "Gewinnt die Gunst von Wismar, Celle den Bergbewohnern oder Likirchen, " ..
        "um im Krieg zwischen Gut und Böse die Oberhand zu gewinnen."
    b[page].position = GetPosition("Briefing")
    b[page].explore  = 2000

    StartBriefing(Umlaute(b))
end

--------------------------------------------------------------------------------
-- Hilfsfunktion: Quest-IDs
--------------------------------------------------------------------------------

function GetQuestId()
    gvMission = gvMission or {}
    gvMission.QuestId = (gvMission.QuestId or 0) + 1
    return gvMission.QuestId
end

--------------------------------------------------------------------------------
-- Quest-Status-Variablen
--------------------------------------------------------------------------------

-- Merkt sich, mit welchem menschlichen Spieler (1 oder 2)
-- die jeweilige KI verbündet ist. 0 = mit niemandem.
AlliedWith = AlliedWith or {}
AlliedWith[5] = AlliedWith[5] or 0   -- Wismar
AlliedWith[6] = AlliedWith[6] or 0   -- Bergbewohner
AlliedWith[7] = AlliedWith[7] or 0   -- Celle
AlliedWith[8] = AlliedWith[8] or 0   -- Likirchen

-- Bergbewohner-Quest:
-- 0 = noch nicht begonnen
-- 1 = Spieler sucht die Vermissten
-- 2 = abgeschlossen, Bergbewohner sind verbündet
BergQuestState = BergQuestState or {}
BergQuestState[1] = BergQuestState[1] or 0
BergQuestState[2] = BergQuestState[2] or 0

-- F4-Quest-IDs für Bergbewohner pro Spieler
BergQuestId = BergQuestId or {}

-- Anzahl der gefundenen Vermissten pro Spieler
BergFoundCount = BergFoundCount or {}
BergFoundCount[1] = BergFoundCount[1] or 0
BergFoundCount[2] = BergFoundCount[2] or 0

-- Welcher Spieler hat welchen Vermissten "gefunden"
-- 0 = noch niemand, 1/2 = entsprechender Spieler
BergMissingOwner = BergMissingOwner or {}
for i = 1, 3 do
    BergMissingOwner[i] = BergMissingOwner[i] or 0
end

-- Likirchen-Quest:
-- LikiGateOwner = Spieler, der als erster beide Wächter überzeugt hat
LikiGateOwner = LikiGateOwner or 0

-- Hat dieser Spieler schon mit Süd-/Nord-Wächter gesprochen?
LikiTalkSouth = LikiTalkSouth or {}
LikiTalkNorth = LikiTalkNorth or {}
LikiTalkSouth[1] = LikiTalkSouth[1] or false
LikiTalkSouth[2] = LikiTalkSouth[2] or false
LikiTalkNorth[1] = LikiTalkNorth[1] or false
LikiTalkNorth[2] = LikiTalkNorth[2] or false

-- Phasen der Likirchen-Quest je Spieler:
-- 0 = Wachen ansprechen
-- 1 = Kathedralen-Quest vom Bischof erhalten
-- 2 = Kathedrale gebaut, mit Bischof erneut gesprochen -> Hauptmann aufsuchen
-- 3 = Likirchen ist verbündet
LikiQuestPhase = LikiQuestPhase or {}
LikiQuestPhase[1] = LikiQuestPhase[1] or 0
LikiQuestPhase[2] = LikiQuestPhase[2] or 0

-- F4-Quest-IDs für Likirchen pro Spieler
LikiQuestId = LikiQuestId or {}

-- Tribute-Status
WismarTributeDone   = WismarTributeDone   or {}
CelleTributeDone    = CelleTributeDone    or {}
WismarTributeActive = WismarTributeActive or {}
CelleTributeActive  = CelleTributeActive  or {}
WismarTributeId     = WismarTributeId     or {}
CelleTributeId      = CelleTributeId      or {}

WismarTributeDone[1] = WismarTributeDone[1] or false
WismarTributeDone[2] = WismarTributeDone[2] or false
CelleTributeDone[1]  = CelleTributeDone[1]  or false
CelleTributeDone[2]  = CelleTributeDone[2]  or false

-- Marker, damit InitQuestSystem nicht doppelt alles erstellt
QuestSystemInitialized = QuestSystemInitialized or false

--------------------------------------------------------------------------------
-- Kleine Hilfsfunktionen
--------------------------------------------------------------------------------

-- Gegenüber von Spieler 1 ist 2, umgekehrt
local function GetOtherHumanPlayer(_pid)
    if _pid == 1 then
        return 2
    elseif _pid == 2 then
        return 1
    end
    return nil
end

-- Ermittelt den Spieler, zu dem der Held gehört
local function GetHeroPlayerId(_HeroId)
    if not _HeroId or _HeroId == 0 then
        return nil
    end
    local pid = GetPlayer(_HeroId)   -- Comfort-Funktion
    if not pid or pid < 1 or pid > 8 then
        return nil
    end
    return pid
end

-- Prüfen, ob eine Kathedrale (PB_Monastery3) gebaut wurde.
local function IsCathedralBuilt(_playerId)
    local count = Logic.GetNumberOfEntitiesOfTypeOfPlayer(_playerId, Entities.PB_Monastery3)
    return count > 0
end

-- Öffnet die Stadttore von Likirchen 
local function OpenLikirchenGatesForPlayer(_playerId)
    local function OpenSingleGate(_name, _openEntityType)
        if IsExisting(_name) then
            local pos = GetPosition(_name)
            DestroyEntity(_name)
            local newId = Logic.CreateEntity(_openEntityType, pos.X, pos.Y, 0, _playerId)
            SetEntityName(newId, _name)
        end
    end

    OpenSingleGate("gate1", Entities.XD_WallStraightGate)
    OpenSingleGate("gate2", Entities.XD_WallStraightGate)
end

--------------------------------------------------------------------------------
-- Diplomatie-Helfer: Haupt-KI pro Spieler + Allianzen
--------------------------------------------------------------------------------

local function GetMainAiForPlayer(pid)
    -- Annahme: Spieler 1 = Varg (3), Spieler 2 = Dario (4)
    if pid == 1 then
        return 3
    elseif pid == 2 then
        return 4
    end
    return nil
end

local function MakeCityAlliedToPlayer(aiId, pid)
    if not pid or (pid ~= 1 and pid ~= 2) then
        return
    end

    AlliedWith[aiId] = pid

    -- Stadt <-> Spieler
    SetFriendly(aiId, pid)
    SetFriendly(pid, aiId)
    ActivateShareExploration(pid, aiId, true)

    -- Stadt <-> eigene Haupt-KI
    local ownAi = GetMainAiForPlayer(pid)
    if ownAi then
        SetFriendly(aiId, ownAi)
        SetFriendly(ownAi, aiId)
        ActivateShareExploration(pid, ownAi, true)
    end

    -- Stadt verfeindet sich mit gegnerischem Spieler + dessen Haupt-KI
    local enemyPid = GetOtherHumanPlayer(pid)
    if enemyPid then
        SetHostile(aiId, enemyPid)
        SetHostile(enemyPid, aiId)

        local enemyAi = GetMainAiForPlayer(enemyPid)
        if enemyAi then
            SetHostile(aiId, enemyAi)
            SetHostile(enemyAi, aiId)
        end
    end
end

--------------------------------------------------------------------------------
-- Init des Questsystems
--------------------------------------------------------------------------------

function InitQuestSystem()
    if QuestSystemInitialized then
        return
    end
    QuestSystemInitialized = true

    -- Alle NPCs registrieren (Ausrufezeichen etc.)
    CreateAlliedNpcs()

    -- Hauptquest "Likirchen" für beide menschlichen Spieler anlegen
    for pid = 1, 2 do
        local hqCount =
            Logic.GetNumberOfEntitiesOfTypeOfPlayer(pid, Entities.PB_Headquarters1) +
            Logic.GetNumberOfEntitiesOfTypeOfPlayer(pid, Entities.PB_Headquarters2) +
            Logic.GetNumberOfEntitiesOfTypeOfPlayer(pid, Entities.PB_Headquarters3)

        if hqCount > 0 then
            local questId = GetQuestId()
            LikiQuestId[pid]    = questId
            LikiQuestPhase[pid] = LikiQuestPhase[pid] or 0

            AddQuestUTF8(
                pid,
                questId,
                SUBQUEST_OPEN,
                "Likirchen",
                "Die Wachen von Likirchen beobachten euch misstrauisch. " ..
                "Sprecht mit beiden Torwächtern vor der Stadt, um ihr Vertrauen zu gewinnen.",
                1
            )
        end
    end

    -- Auto-Job für Likirchen-Kathedrale
    StartSimpleJob("Job_LikirchenAutoAlliance")

    -- Diplomatie-Sync Bergbewohner / Likirchen
    StartSimpleJob("Job_AlliedCityDiplomacySync")
end

--------------------------------------------------------------------------------
-- NPCs anlegen
--------------------------------------------------------------------------------

function CreateAlliedNpcs()
    local npc

    -- Wismar (Player 5)
    npc = {}
    npc.name     = "Leader_Wismar"
    npc.callback = Leader_Wismar
    CreateNPC(npc)

    -- Celle (Player 7)
    npc = {}
    npc.name     = "Leader_Celle"
    npc.callback = Leader_Celle
    CreateNPC(npc)

    -- Bergbewohner (Player 6)
    npc = {}
    npc.name     = "Leader_Bergbewohner"
    npc.callback = Leader_Bergbewohner
    CreateNPC(npc)

    -- Likirchen – Torwächter
    npc = {}
    npc.name     = "Waechter_1"
    npc.callback = Waechter_1
    CreateNPC(npc)

    npc = {}
    npc.name     = "Waechter_2"
    npc.callback = Waechter_2
    CreateNPC(npc)

    -- Likirchen – Bischof und Hauptmann
    npc = {}
    npc.name     = "Bischof_Likirchen"
    npc.callback = Bischof_Likirchen
    CreateNPC(npc)

    npc = {}
    npc.name     = "Hauptmann_Likirchen"
    npc.callback = Hauptmann_Likirchen
    CreateNPC(npc)

    -- Vermisste Bergbewohner (3 Stück)
    npc = {}
    npc.name     = "Berg_Vermisst1"
    npc.callback = MissingBergSettler1
    CreateNPC(npc)

    npc = {}
    npc.name     = "Berg_Vermisst2"
    npc.callback = MissingBergSettler2
    CreateNPC(npc)

    npc = {}
    npc.name     = "Berg_Vermisst3"
    npc.callback = MissingBergSettler3
    CreateNPC(npc)
end

--------------------------------------------------------------------------------
-- AddTribute-Wrapper (Siedler-Originalfunktion)
--------------------------------------------------------------------------------

function AddTribute(_tribute)
    assert(type(_tribute) == "table", "Tribut muss ein Table sein")
    assert(type(_tribute.text) == "string", "Tribut.text muss ein String sein")
    assert(type(_tribute.cost) == "table", "Tribut.cost muss ein Table sein")

    -- sowohl tribute.playerId als auch tribute.pId unterstützen
    local pId = _tribute.playerId or _tribute.pId
    assert(type(pId) == "number", "Tribut.playerId/pId muss eine Nummer sein")
    assert(not _tribute.Tribute, "Tribut.Tribute darf nicht vorbelegt sein")

    uniqueTributeCounter = uniqueTributeCounter or 1
    _tribute.Tribute = uniqueTributeCounter
    uniqueTributeCounter = uniqueTributeCounter + 1

    local tResCost = {}
    for k, v in pairs(_tribute.cost) do
        assert(ResourceType[k])
        assert(type(v) == "number")
        table.insert(tResCost, ResourceType[k])
        table.insert(tResCost, v)
    end

    Logic.AddTribute(
        pId,
        _tribute.Tribute,
        0,
        0,
        Umlaute(_tribute.text),
        unpack(tResCost)
    )

    -- Für SetupTributePaid kompatibel bleiben
    _tribute.pId      = pId
    _tribute.playerId = pId

    -- SetupTributePaid kommt aus NPC.lua (verknüpft Callback)
    SetupTributePaid(_tribute)

    return _tribute.Tribute
end

--------------------------------------------------------------------------------
-- Rivalen / Tribute Wismar & Celle
--------------------------------------------------------------------------------

local function Wismar_AllyWithPlayer(pid)
    -- Wismar kann nur EINEM Spieler folgen
    if AlliedWith[5] ~= 0 and AlliedWith[5] ~= pid then
        return
    end

    MakeCityAlliedToPlayer(5, pid)
    WismarTributeDone[pid] = true

    -- Spieler hat sich für Wismar entschieden:
    -- -> ggf. laufenden Celle-Tribut dieses Spielers entfernen
    if CelleTributeActive[pid] and CelleTributeId[pid] then
        Logic.RemoveTribute(pid, CelleTributeId[pid])
    end
    CelleTributeActive[pid] = false
    CelleTributeId[pid]     = nil
    CelleTributeDone[pid]   = true   -- für diesen Spieler gibt es kein Celle-Angebot mehr

    -- Rivalität zu Celle: Stadt + Spieler werden Feinde
    SetHostile(7, pid)
    SetHostile(pid, 7)

    -- Wismar-Tribut für den anderen Spieler entfernen/verbieten
    local other = GetOtherHumanPlayer(pid)
    if other and not WismarTributeDone[other] then
        if WismarTributeActive[other] and WismarTributeId[other] then
            Logic.RemoveTribute(other, WismarTributeId[other])
        end
        WismarTributeActive[other] = false
        WismarTributeDone[other]   = true
        WismarTributeId[other]     = nil
    end
end

local function Celle_AllyWithPlayer(pid)
    -- Celle kann nur EINEM Spieler folgen
    if AlliedWith[7] ~= 0 and AlliedWith[7] ~= pid then
        return
    end

    MakeCityAlliedToPlayer(7, pid)
    CelleTributeDone[pid] = true

    -- Spieler hat sich für Celle entschieden:
    -- -> ggf. laufenden Wismar-Tribut dieses Spielers entfernen
    if WismarTributeActive[pid] and WismarTributeId[pid] then
        Logic.RemoveTribute(pid, WismarTributeId[pid])
    end
    WismarTributeActive[pid] = false
    WismarTributeId[pid]     = nil
    WismarTributeDone[pid]   = true   -- für diesen Spieler gibt es kein Wismar-Angebot mehr

    -- Rivalität zu Wismar: Stadt + Spieler werden Feinde
    SetHostile(5, pid)
    SetHostile(pid, 5)

    -- Celle-Tribut für den anderen Spieler entfernen/verbieten
    local other = GetOtherHumanPlayer(pid)
    if other and not CelleTributeDone[other] then
        if CelleTributeActive[other] and CelleTributeId[other] then
            Logic.RemoveTribute(other, CelleTributeId[other])
        end
        CelleTributeActive[other] = false
        CelleTributeDone[other]   = true
        CelleTributeId[other]     = nil
    end
end

local function StartWismarTributeForPlayer(pid)
    if pid ~= 1 and pid ~= 2 then
        return
    end
    if WismarTributeDone[pid] or WismarTributeActive[pid] then
        return
    end

    -- Wismar ist bereits mit einem anderen Spieler verbündet
    if AlliedWith[5] ~= 0 and AlliedWith[5] ~= pid then
        return
    end

    -- dieser Spieler hat sich bereits für Celle entschieden
    if AlliedWith[7] == pid then
        return
    end

    local tribute    = {}
    tribute.playerId = pid
    tribute.text     = "Zahlt 500 Taler, dann wird Wismar an eurer Seite kämpfen."
    tribute.cost     = { Gold = 500 }

    if pid == 1 then
        tribute.Callback = Tribute_Wismar_P1_Paid
    else
        tribute.Callback = Tribute_Wismar_P2_Paid
    end

    WismarTributeActive[pid] = true
    WismarTributeId[pid]     = AddTribute(tribute)
end

local function StartCelleTributeForPlayer(pid)
    if pid ~= 1 and pid ~= 2 then
        return
    end
    if CelleTributeDone[pid] or CelleTributeActive[pid] then
        return
    end

    -- Celle ist bereits mit einem anderen Spieler verbündet
    if AlliedWith[7] ~= 0 and AlliedWith[7] ~= pid then
        return
    end

    -- dieser Spieler hat sich bereits für Wismar entschieden
    if AlliedWith[5] == pid then
        return
    end

    local tribute    = {}
    tribute.playerId = pid
    tribute.text     = "Zahlt 500 Taler, dann wird Celle an eurer Seite kämpfen."
    tribute.cost     = { Gold = 500 }

    if pid == 1 then
        tribute.Callback = Tribute_Celle_P1_Paid
    else
        tribute.Callback = Tribute_Celle_P2_Paid
    end

    CelleTributeActive[pid] = true
    CelleTributeId[pid]     = AddTribute(tribute)
end

function Tribute_Wismar_P1_Paid()
    local pid = 1
    WismarTributeActive[pid] = false
    WismarTributeId[pid]    = nil
    Wismar_AllyWithPlayer(pid)

    NpcSayOnePage_BigDialog(
        "Leader_Wismar",
        "Anführer von Wismar",
        "Ihr habt den Tribut gezahlt. Von nun an stehen unsere Truppen an eurer Seite. " ..
        "Celle wird euch dafür wohl als Feind betrachten."
    )
end

function Tribute_Wismar_P2_Paid()
    local pid = 2
    WismarTributeActive[pid] = false
    WismarTributeId[pid]    = nil
    Wismar_AllyWithPlayer(pid)

    NpcSayOnePage_BigDialog(
        "Leader_Wismar",
        "Anführer von Wismar",
        "Ihr habt auch diesen Tribut gezahlt. Von nun an machen wir auch alle anderen Feinde platt. "
    )
end

function Tribute_Celle_P1_Paid()
    local pid = 1
    CelleTributeActive[pid] = false
    CelleTributeId[pid]    = nil
    Celle_AllyWithPlayer(pid)

    NpcSayOnePage_BigDialog(
        "Leader_Celle",
        "Anführer von Celle",
        "Ihr habt den Tribut gezahlt. Abgemacht – von nun an steht Celle hinter euch. " ..
        "Wismar wird euch dafür als Feind betrachten."
    )
end

function Tribute_Celle_P2_Paid()
    local pid = 2
    CelleTributeActive[pid] = false
    CelleTributeId[pid]    = nil
    Celle_AllyWithPlayer(pid)

    NpcSayOnePage_BigDialog(
        "Leader_Celle",
        "Anführer von Celle",
        "Ihr habt nochmal gezahlt. Abgemacht – dann lassen wir eure Feinde nicht mehr passieren. "
    )
end

function Leader_Wismar(_NpcId, _HeroId)
    local pid = GetHeroPlayerId(_HeroId)
    if pid ~= 1 and pid ~= 2 then
        return
    end

    if AlliedWith[5] ~= 0 and AlliedWith[5] ~= pid then
        NpcSayOnePage_BigDialog(
            "Leader_Wismar",
            "Anführer von Wismar",
            "Wir stehen bereits an der Seite eines anderen Herrschers."
        )
        return
    end

    -- Spieler ist bereits mit Celle verbündet
    if AlliedWith[7] == pid and AlliedWith[5] ~= pid then
        NpcSayOnePage_BigDialog(
            "Leader_Wismar",
            "Anführer von Wismar",
            "Ihr habt euch bereits mit Celle verbündet. Wir können nicht beide Reiche unterstützen."
        )
        return
    end

    if AlliedWith[5] == pid then
        NpcSayOnePage_BigDialog(
            "Leader_Wismar",
            "Anführer von Wismar",
            "Unsere Banner wehen bereits an eurer Seite."
        )
        return
    end

    if WismarTributeActive[pid] then
        NpcSayOnePage_BigDialog(
            "Leader_Wismar",
            "Anführer von Wismar",
            "Unser Angebot steht. Der geforderte Tribut ist in eurer Übersicht vermerkt."
        )
        return
    end

    StartWismarTributeForPlayer(pid)

    NpcSayOnePage_BigDialog(
        "Leader_Wismar",
        "Anführer von Wismar",
        "Ihr wollt, dass Wismar an eurer Seite kämpft? Zahlt 500 Taler " ..
        "als Tribut – ihr findet das Angebot in eurer Tributenliste."
    )
end

function Leader_Celle(_NpcId, _HeroId)
    local pid = GetHeroPlayerId(_HeroId)
    if pid ~= 1 and pid ~= 2 then
        return
    end

    if AlliedWith[7] ~= 0 and AlliedWith[7] ~= pid then
        NpcSayOnePage_BigDialog(
            "Leader_Celle",
            "Anführer von Celle",
            "Wir stehen bereits im Dienst eines anderen Herrschers."
        )
        return
    end

    -- Spieler ist bereits mit Wismar verbündet
    if AlliedWith[5] == pid and AlliedWith[7] ~= pid then
        NpcSayOnePage_BigDialog(
            "Leader_Celle",
            "Anführer von Celle",
            "Ihr habt euch bereits mit Wismar verbündet. Wir können nicht auf beiden Seiten kämpfen."
        )
        return
    end

    if AlliedWith[7] == pid then
        NpcSayOnePage_BigDialog(
            "Leader_Celle",
            "Anführer von Celle",
            "Wir kämpfen bereits an eurer Seite."
        )
        return
    end

    if CelleTributeActive[pid] then
        NpcSayOnePage_BigDialog(
            "Leader_Celle",
            "Anführer von Celle",
            "Der geforderte Tribut ist bereits vermerkt. Zahlt ihn, wenn ihr unsere Hilfe wollt."
        )
        return
    end

    StartCelleTributeForPlayer(pid)

    NpcSayOnePage_BigDialog(
        "Leader_Celle",
        "Anführer von Celle",
        "Ihr wollt, dass Celle an eurer Seite kämpft? Zahlt 500 Taler " ..
        "als Tribut – ihr findet das Angebot in eurer Tributenliste."
    )
end

--------------------------------------------------------------------------------
-- Bergbewohner-Quest 
--------------------------------------------------------------------------------

local BERG_MISSING_TOTAL = 3

local function Berg_Finish(winnerPid)
    if BergQuestFinished then
        return
    end
    BergQuestFinished = true
    AlliedWith[6]     = winnerPid

    -- Für beide Spieler ist die Quest damit entschieden
    BergQuestState[1] = 2
    BergQuestState[2] = 2

    -- Allianz, Diplomatie-Grundsetup
    SetFriendly(6, winnerPid)
    SetFriendly(winnerPid, 6)
    ActivateShareExploration(winnerPid, 6, true)

    local ownAi = GetMainAiForPlayer(winnerPid)
    if ownAi then
        SetFriendly(6, ownAi)
        SetFriendly(ownAi, 6)
        ActivateShareExploration(ownAi, 6, true)
    end

    local enemyPid = GetOtherHumanPlayer(winnerPid)
    if enemyPid then
        SetHostile(6, enemyPid)
        SetHostile(enemyPid, 6)
        local enemyAi = GetMainAiForPlayer(enemyPid)
        if enemyAi then
            SetHostile(6, enemyAi)
            SetHostile(enemyAi, 6)
        end
    end

    for pid = 1, 2 do
        if BergQuestId[pid] then
            Logic.RemoveQuest(pid, BergQuestId[pid])
            BergQuestId[pid] = nil
        end
    end

    local names = {
        "Leader_Bergbewohner",
        "Berg_Vermisst1",
        "Berg_Vermisst2",
        "Berg_Vermisst3"
    }
    for _, name in ipairs(names) do
        if IsExisting(name) then
            DisableNpcMarker(GetEntityId(name))
        end
    end

    NpcSayOnePage_BigDialog(
        "Leader_Bergbewohner",
        "Anführer der Bergbewohner",
        "Ihr habt alle unsere Leute gefunden. Von nun an kämpfen wir Bergbewohner an eurer Seite."
    )
end

local function UpdateBergQuestForPlayer(pid)
    if pid ~= 1 and pid ~= 2 then
        return
    end
    if BergQuestFinished then
        return
    end
    if BergQuestState[pid] == 0 then
        return
    end

    if BergQuestId[pid] then
        Logic.RemoveQuest(pid, BergQuestId[pid])
    end
    local questId = GetQuestId()
    BergQuestId[pid] = questId

    local found = BergFoundCount[pid] or 0
    local text =
        "Der Anführer der Bergbewohner bittet euch, drei vermisste Leute aufzuspüren. " ..
        "Ihr habt bisher " .. found .. " von 3 gefunden. "

    if found < BERG_MISSING_TOTAL then
        text = text ..
               "Sucht nach den übrigen Vermissten und sprecht mit ihnen, damit sie wissen, dass Hilfe unterwegs ist."
    else
        text = text ..
               "Ihr habt alle Vermissten gefunden. Das Bergvolk wird sich bald entscheiden."
    end

    AddQuestUTF8(
        pid,
        questId,
        SUBQUEST_OPEN,
        "Die Vermissten der Bergbewohner",
        text,
        1
    )

    if not BergQuestFinished then
        EnsureNpcMarkerOn("Leader_Bergbewohner")
        EnsureNpcMarkerOn("Berg_Vermisst1")
        EnsureNpcMarkerOn("Berg_Vermisst2")
        EnsureNpcMarkerOn("Berg_Vermisst3")
    end
end

function Leader_Bergbewohner(_NpcId, _HeroId)
    EnsureNpcMarkerOn("Leader_Bergbewohner")
    local pid = GetHeroPlayerId(_HeroId)
    if pid ~= 1 and pid ~= 2 then
        return
    end

    if BergQuestFinished then
        if AlliedWith[6] == pid then
            NpcSayOnePage_BigDialog(
                "Leader_Bergbewohner",
                "Anführer der Bergbewohner",
                "Unsere Krieger stehen bereits in eurem Dienst."
            )
        else
            NpcSayOnePage_BigDialog(
                "Leader_Bergbewohner",
                "Anführer der Bergbewohner",
                "Wir haben unser Wort bereits einem anderen Herrscher gegeben."
            )
        end
        return
    end

    if BergQuestState[pid] == 0 then
        BergQuestState[pid] = 1
        BergFoundCount[pid] = BergFoundCount[pid] or 0
        UpdateBergQuestForPlayer(pid)

        NpcSayOnePage_BigDialog(
            "Leader_Bergbewohner",
            "Anführer der Bergbewohner",
            "Drei unserer Leute sind verschwunden. Bitte helft uns, sie wiederzufinden. " ..
            "Durchstreift das Gebiet und sprecht mit jedem unserer Leute, den ihr findet."
        )
        return
    end

    if BergQuestState[pid] == 1 then
        local found = BergFoundCount[pid] or 0
        if found < BERG_MISSING_TOTAL then
            NpcSayOnePage_BigDialog(
                "Leader_Bergbewohner",
                "Anführer der Bergbewohner",
                "Ihr habt noch nicht alle Vermissten gefunden. Bisher habt ihr " ..
                found .. " von 3 entdeckt. Sucht weiter nach ihnen."
            )
            return
        else
            -- Spieler hat 3/3 – Quest wird global entschieden
            Berg_Finish(pid)
            return
        end
    end
end

local function HandleMissingBergSettler(_index, _NpcEntityName, _NpcId, _HeroId)
    EnsureNpcMarkerOn(_NpcEntityName)
    local pid = GetHeroPlayerId(_HeroId)
    if pid ~= 1 and pid ~= 2 then
        return
    end

    if BergQuestFinished then
        if AlliedWith[6] == pid then
            NpcSayOnePage_BigDialog(
                _NpcEntityName,
                "Vermisster Bergbewohner",
                "Ihr habt uns bereits gefunden. Wir folgen dem Ruf unseres Anführers."
            )
        else
            NpcSayOnePage_BigDialog(
                _NpcEntityName,
                "Vermisster Bergbewohner",
                "Unsere Angelegenheit ist bereits entschieden."
            )
        end
        return
    end

    if BergQuestState[pid] == 0 then
        NpcSayOnePage_BigDialog(
            _NpcEntityName,
            "Vermisster Bergbewohner",
            "Wir danken euch, aber unser Anführer hat euch noch nicht zu uns geschickt."
        )
        return
    end

    if BergMissingOwner[_index] ~= 0 then
        if BergMissingOwner[_index] == pid then
            NpcSayOnePage_BigDialog(
                _NpcEntityName,
                "Vermisster Bergbewohner",
                "Wir haben bereits miteinander gesprochen. Erzählt unserem Anführer, dass ihr mich gefunden habt."
            )
        else
            NpcSayOnePage_BigDialog(
                _NpcEntityName,
                "Vermisster Bergbewohner",
                "Ein anderer Herrscher hat mich bereits gefunden."
            )
        end
        return
    end

    local text
    if _index == 1 then
        text = "Endlich jemand! Ich war unterwegs, um die Umgebung auszukundschaften. " ..
               "Sagt unserem Anführer, dass ihr mich gefunden habt. Ich bleibe hier und warte."
    elseif _index == 2 then
        text = "Die Beine wollen nicht mehr so wie früher... Ich bleibe hier. " ..
               "Sagt dem Anführer, dass ihr mich gefunden habt."
    else
        text = "Schon wieder Arbeit? Gut, gut... Sagt unserem Anführer, dass ihr mich gefunden habt."
    end

    NpcSayOnePage_BigDialog(
        _NpcEntityName,
        "Vermisster Bergbewohner",
        text
    )

    BergMissingOwner[_index] = pid
    BergFoundCount[pid]      = (BergFoundCount[pid] or 0) + 1

    UpdateBergQuestForPlayer(pid)

    if BergFoundCount[pid] >= BERG_MISSING_TOTAL and not BergQuestFinished then
        Berg_Finish(pid)
    end
end

function MissingBergSettler1(_NpcId, _HeroId)
    HandleMissingBergSettler(1, "Berg_Vermisst1", _NpcId, _HeroId)
end
function MissingBergSettler2(_NpcId, _HeroId)
    HandleMissingBergSettler(2, "Berg_Vermisst2", _NpcId, _HeroId)
end
function MissingBergSettler3(_NpcId, _HeroId)
    HandleMissingBergSettler(3, "Berg_Vermisst3", _NpcId, _HeroId)
end

--------------------------------------------------------------------------------
-- Likirchen
--------------------------------------------------------------------------------

function Waechter_1(_NpcId, _HeroId)
    EnsureNpcMarkerOn("Waechter_1")
    local pid = GetHeroPlayerId(_HeroId)
    if pid ~= 1 and pid ~= 2 then
        return
    end

    -- Kein „anderer Herrscher“-Block mehr: beide Spieler können sprechen

    LikiTalkSouth[pid] = true

    NpcSayOnePage_BigDialog(
        "Waechter_1",
        "Stadtwache von Likirchen",
        "Wir haben euren Namen vernommen. Sprecht auch mit meinem Kameraden am anderen Tor."
    )

    if LikiTalkSouth[pid] and LikiTalkNorth[pid] and LikiGateOwner == 0 then
        LikiGateOwner = pid
        OpenLikirchenGatesForPlayer(pid)

        if LikiQuestId[pid] then
            Logic.RemoveQuest(pid, LikiQuestId[pid])
        end
        local questId = GetQuestId()
        LikiQuestId[pid]    = questId
        LikiQuestPhase[pid] = 0

        AddQuestUTF8(
            pid,
            questId,
            SUBQUEST_OPEN,
            "Likirchen",
            "Die Tore von Likirchen stehen euch nun offen. " ..
            "Sucht den Bischof in der Stadt auf und sprecht mit ihm.",
            1
        )

        EnsureNpcMarkerOn("Bischof_Likirchen")
        EnsureNpcMarkerOn("Hauptmann_Likirchen")
    end
end

function Waechter_2(_NpcId, _HeroId)
    EnsureNpcMarkerOn("Waechter_2")
    local pid = GetHeroPlayerId(_HeroId)
    if pid ~= 1 and pid ~= 2 then
        return
    end

    -- Kein „anderer Herrscher“-Block mehr: beide Spieler können sprechen

    LikiTalkNorth[pid] = true

    NpcSayOnePage_BigDialog(
        "Waechter_2",
        "Stadtwache von Likirchen",
        "Euer Name ist vermerkt. Hat euch mein Kamerad am südlichen Tor gesehen? " ..
        "Dann melden wir euch der Stadt."
    )

    if LikiTalkSouth[pid] and LikiTalkNorth[pid] and LikiGateOwner == 0 then
        LikiGateOwner = pid
        OpenLikirchenGatesForPlayer(pid)

        if LikiQuestId[pid] then
            Logic.RemoveQuest(pid, LikiQuestId[pid])
        end
        local questId = GetQuestId()
        LikiQuestId[pid]    = questId
        LikiQuestPhase[pid] = 0

        AddQuestUTF8(
            pid,
            questId,
            SUBQUEST_OPEN,
            "Likirchen",
            "Die Tore von Likirchen stehen euch nun offen. " ..
            "Sucht den Bischof in der Stadt auf und sprecht mit ihm.",
            1
        )

        EnsureNpcMarkerOn("Bischof_Likirchen")
        EnsureNpcMarkerOn("Hauptmann_Likirchen")
    end
end

function Bischof_Likirchen(_NpcId, _HeroId)
    EnsureNpcMarkerOn("Bischof_Likirchen")
    local pid = GetHeroPlayerId(_HeroId)
    if pid ~= 1 and pid ~= 2 then
        return
    end

    -- Kein Block mehr, der nur LikiGateOwner zulässt:
    -- Wichtig ist nur, dass der Spieler seine eigene Bischof-Quest startet.

    if AlliedWith[8] ~= 0 then
        if AlliedWith[8] == pid then
            NpcSayOnePage_BigDialog(
                "Bischof_Likirchen",
                "Bischof von Likirchen",
                "Möge eure Kathedrale euren Sieg besiegeln."
            )
        else
            NpcSayOnePage_BigDialog(
                "Bischof_Likirchen",
                "Bischof von Likirchen",
                "Unsere Gebete gelten bereits einem anderen Reich."
            )
        end
        return
    end

    if LikiQuestPhase[pid] == 0 then
        LikiQuestPhase[pid] = 1

        if LikiQuestId[pid] then
            Logic.RemoveQuest(pid, LikiQuestId[pid])
        end
        local questId = GetQuestId()
        LikiQuestId[pid] = questId

        AddQuestUTF8(
            pid,
            questId,
            SUBQUEST_OPEN,
            "Likirchen – Kathedrale",
            "Der Bischof verlangt den Bau einer Kathedrale in eurem Einflussbereich. " ..
            "Errichtet die Kathedrale und sprecht danach erneut mit ihm.",
            1
        )

        NpcSayOnePage_BigDialog(
            "Bischof_Likirchen",
            "Bischof von Likirchen",
            "Errichtet eine Kathedrale in eurem Einflussbereich und kehrt danach zu mir zurück."
        )
        return
    end

    if LikiQuestPhase[pid] == 1 then
        if not IsCathedralBuilt(pid) then
            NpcSayOnePage_BigDialog(
                "Bischof_Likirchen",
                "Bischof von Likirchen",
                "Noch steht keine Kathedrale in eurem Einflussbereich. " ..
                "Errichtet sie, und ich will für euch sprechen."
            )
            return
        end

        LikiQuestPhase[pid] = 2

        if LikiQuestId[pid] then
            Logic.RemoveQuest(pid, LikiQuestId[pid])
        end
        local questId = GetQuestId()
        LikiQuestId[pid] = questId

        AddQuestUTF8(
            pid,
            questId,
            SUBQUEST_OPEN,
            "Likirchen – Bündnis",
            "Der Bischof steht nun auf eurer Seite. " ..
            "Sprecht mit dem Hauptmann von Likirchen, um ein militärisches Bündnis zu schließen.",
            1
        )

        EnsureNpcMarkerOn("Hauptmann_Likirchen")

        NpcSayOnePage_BigDialog(
            "Bischof_Likirchen",
            "Bischof von Likirchen",
            "Ihr habt eine Kathedrale errichtet. Sprecht nun mit dem Hauptmann von Likirchen."
        )
        return
    end

    NpcSayOnePage_BigDialog(
        "Bischof_Likirchen",
        "Bischof von Likirchen",
        "Unsere geistliche Gunst habt ihr. Der Hauptmann erwartet euch."
    )
end

function Hauptmann_Likirchen(_NpcId, _HeroId)
    EnsureNpcMarkerOn("Hauptmann_Likirchen")
    local pid = GetHeroPlayerId(_HeroId)
    if pid ~= 1 and pid ~= 2 then
        return
    end

    -- Kein Block mehr, der nur LikiGateOwner zulässt

    if AlliedWith[8] ~= 0 then
        if AlliedWith[8] == pid then
            NpcSayOnePage_BigDialog(
                "Hauptmann_Likirchen",
                "Hauptmann von Likirchen",
                "Unsere Truppen stehen zu euren Diensten."
            )
        else
            NpcSayOnePage_BigDialog(
                "Hauptmann_Likirchen",
                "Hauptmann von Likirchen",
                "Unsere Klingen dienen bereits einem anderen Herrscher."
            )
        end
        return
    end

    if LikiQuestPhase[pid] < 2 then
        NpcSayOnePage_BigDialog(
            "Hauptmann_Likirchen",
            "Hauptmann von Likirchen",
            "Der Bischof prüft zuerst euren Glauben. Errichtet eine Kathedrale und sprecht mit ihm, " ..
            "bevor ihr zu mir kommt."
        )
        return
    end

    if LikiQuestPhase[pid] == 2 then
        LikiQuestPhase[pid] = 3
        AlliedWith[8]       = pid

        -- Allianz, Diplomatie-Grundsetup
        SetFriendly(8, pid)
        SetFriendly(pid, 8)
        ActivateShareExploration(pid, 8, true)

        local ownAi = GetMainAiForPlayer(pid)
        if ownAi then
            SetFriendly(8, ownAi)
            SetFriendly(ownAi, 8)
            ActivateShareExploration(ownAi, 8, true)
        end

        local enemyPid = GetOtherHumanPlayer(pid)
        if enemyPid then
            SetHostile(8, enemyPid)
            SetHostile(enemyPid, 8)
            local enemyAi = GetMainAiForPlayer(enemyPid)
            if enemyAi then
                SetHostile(8, enemyAi)
                SetHostile(enemyAi, 8)
            end
        end

        if LikiQuestId[pid] then
            Logic.RemoveQuest(pid, LikiQuestId[pid])
            LikiQuestId[pid] = nil
        end

        if IsExisting("Bischof_Likirchen") then
            DisableNpcMarker(GetEntityId("Bischof_Likirchen"))
        end
        if IsExisting("Hauptmann_Likirchen") then
            DisableNpcMarker(GetEntityId("Hauptmann_Likirchen"))
        end

        NpcSayOnePage_BigDialog(
            "Hauptmann_Likirchen",
            "Hauptmann von Likirchen",
            "Ihr habt unseren Glauben und unsere Stadt geeint. Likirchen schließt sich euch an."
        )
        return
    end

    NpcSayOnePage_BigDialog(
        "Hauptmann_Likirchen",
        "Hauptmann von Likirchen",
        "Für Likirchen!"
    )
end

--------------------------------------------------------------------------------
-- Likirchen Auto-Allianz (Backup) 
--------------------------------------------------------------------------------
function Job_LikirchenAutoAlliance()
    if AlliedWith[8] ~= 0 then
        return true
    end

    for pid = 1, 2 do
        if LikiGateOwner == pid and LikiQuestPhase[pid] >= 1 and IsCathedralBuilt(pid) then
            LikiQuestPhase[pid] = 3
            AlliedWith[8]       = pid

            SetFriendly(8, pid)
            SetFriendly(pid, 8)
            ActivateShareExploration(pid, 8, true)

            local ownAi = GetMainAiForPlayer(pid)
            if ownAi then
                SetFriendly(8, ownAi)
                SetFriendly(ownAi, 8)
                ActivateShareExploration(ownAi, 8, true)
            end

            local enemyPid = GetOtherHumanPlayer(pid)
            if enemyPid then
                SetHostile(8, enemyPid)
                SetHostile(enemyPid, 8)
                local enemyAi = GetMainAiForPlayer(enemyPid)
                if enemyAi then
                    SetHostile(8, enemyAi)
                    SetHostile(enemyAi, 8)
                end
            end

            if LikiQuestId[pid] then
                Logic.RemoveQuest(pid, LikiQuestId[pid])
                LikiQuestId[pid] = nil
            end

            if IsExisting("Bischof_Likirchen") then
                DisableNpcMarker(GetEntityId("Bischof_Likirchen"))
            end
            if IsExisting("Hauptmann_Likirchen") then
                DisableNpcMarker(GetEntityId("Hauptmann_Likirchen"))
            end

            return true
        end
    end

    return false
end

--------------------------------------------------------------------------------
-- Diplomatie-Sync: Bergvolk (6) & Likirchen (8) folgen Feinden des Verbündeten
--------------------------------------------------------------------------------

function Job_AlliedCityDiplomacySync()
    local function SyncCity(cityId)
        local ally = AlliedWith[cityId]
        if not ally or ally == 0 then
            return
        end
        for other = 1, 8 do
            if other ~= cityId and other ~= ally then
                -- Annahme: 2 = freundlich, 3 = feindlich
                local state = Logic.GetDiplomacyState(ally, other)
                if state == 3 then
                    SetHostile(cityId, other)
                    SetHostile(other, cityId)
                elseif state == 2 then
                    SetFriendly(cityId, other)
                    SetFriendly(other, cityId)
                end
            end
        end
    end

    SyncCity(6) -- Bergbewohner
    SyncCity(8) -- Likirchen
end

--------------------------------------------------------------------------------
-- Sieg/Niederlage für lokalen Spieler über HQs
--------------------------------------------------------------------------------

HQResultDone = HQResultDone or false

local function HasAnyHQ(pid)
    return (Logic.GetNumberOfEntitiesOfTypeOfPlayer(pid, Entities.PB_Headquarters1)
        + Logic.GetNumberOfEntitiesOfTypeOfPlayer(pid, Entities.PB_Headquarters2)
        + Logic.GetNumberOfEntitiesOfTypeOfPlayer(pid, Entities.PB_Headquarters3)) > 0
end

--------------------------------------------------------------------------------
-- MapEditor-Texte
--------------------------------------------------------------------------------

MapEditor_QuestTitle       = Umlaute("Krieg zwischen Gut und Böse")
MapEditor_QuestDescription =
    Umlaute("Besiegt die gegnerische Seite im Krieg zwischen Gut und Böse. " ..
            "Verbündet euch mit den neutralen Fraktionen, um eure Chancen zu erhöhen.")