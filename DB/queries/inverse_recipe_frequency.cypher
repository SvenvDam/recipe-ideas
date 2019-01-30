// calculate inverse recipe frequencies and set as attribute of ingredients
MATCH ()-[r]->(n:Ingredient)
WITH 1.0/COUNT(r) as irf
SET n.irf = irf
RETURN n