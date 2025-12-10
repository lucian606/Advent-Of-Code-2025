from shapely.geometry import Point
from shapely.geometry.polygon import Polygon
from shapely.geometry import box as Box

from multiprocessing import Manager, Pool

def getRectangleArea(firstCorner, secondCorner):
    return (abs(firstCorner.x - secondCorner.x) + 1) * (abs(firstCorner.y - secondCorner.y) + 1)

def checkRectangle(polygon, firstCorner, secondCorner, validRectangles):
    minX = int(min(firstCorner.x, secondCorner.x))
    maxX = int(max(firstCorner.x, secondCorner.x))
    minY = int(min(firstCorner.y, secondCorner.y))
    maxY = int(max(firstCorner.y, secondCorner.y))

    rectangle = Box(minX, minY, maxX, maxY)

    if polygon.contains(rectangle):
        area = getRectangleArea(firstCorner, secondCorner)
        validRectangles.append(area)

def solvePartTwo(path):
    with open(path) as file:
        lines = file.readlines()
        points = []

        for line in lines:
            x, y = line.strip().split(',')
            points.append(Point(int(x), int(y)))

        polygon = Polygon(points)

        rectanglesToCheck = []

        with Manager() as manager:
            validRectangles = manager.list()

            for i in range(len(points)):
                for j in range(i + 1, len(points)):
                    rectanglesToCheck.append((polygon, points[i], points[j], validRectangles))
            with Pool(8) as pool:
                pool.starmap(checkRectangle, rectanglesToCheck)

            validRectangles.sort(reverse=True)
            print(f"Largest valid rectangle area: {validRectangles[0]}")


if __name__ == "__main__":
    #solvePartTwo("../../test/inputs/day09_example.in")
    solvePartTwo("../inputs/day09.in")