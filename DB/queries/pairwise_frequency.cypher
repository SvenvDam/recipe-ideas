MATCH (i1:Ingredient)<-[]-(r1:Recipe)
MATCH (i2:Ingredient)<-[]-(r2:Recipe)
MATCH (i1)<-[]-(r3:Recipe)-[]->(i2)
RETURN i1, i2, toFloat(2 * count(distinct r3)) / toFloat(count(distinct r1) + count(distinct r2)) as frac
