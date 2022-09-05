alter table Game
	add constraint fk_developerid FOREIGN KEY (developerid) REFERENCES Developer(Developerid)ON DELETE CASCADE;

alter table Customer
	add constraint fk_dno FOREIGN KEY (gameid) REFERENCES Game(Gameid)ON DELETE CASCADE;
  