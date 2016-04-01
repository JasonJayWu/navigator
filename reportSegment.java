    /** reportSegment using System.out.println. */
    int reportSegment(int seq, int from, List<Integer> segment) {
        for (int i = 0; i < segment.size() - 2; i += 1) {
            double dist = _map.getLabel(segment.get(i), segment.get(i + 1)).length();

            if (i == segment.size() - 2) {
                Road road = _map.getLabel(segment.get(i), segment.get(i + 1));
                DecimalFormat numberFormat = new DecimalFormat("#.0");
                Double roadLength = Math.round(road.length() * 10.0) / 10.0;
                Location location = _map.getLabel(segment.get(i + 1));
                System.out.printf("%d. Take %s %s for %.1f miles to %s.", seq,
                    road.toString(), road.direction().fullName(), roadLength,
                    location.toString());
                System.out.println(seq + ". Take " + road.toString() + " " + road.direction()
                .fullName() + " for " + numberFormat.format(roadLength) + " miles to " +
                location.toString() + ".");
                seq += 1;
                return seq;
            }

            if (i < segment.size() - 2 && 
                _map.getLabel(segment.get(i), segment.get(i + 1)).toString()
                    .equals(_map.getLabel(segment.get(i + 1), segment.get(i + 2)).toString())
                        && _map.getLabel(segment.get(i), segment.get(i + 1)).direction().fullName()
                        .equals(_map.getLabel(segment.get(i + 1), segment.get(i + 2)).direction()
                            .fullName())) {
                while (_map.getLabel(segment.get(i), segment.get(i + 1)).toString()
                    .equals(_map.getLabel(segment.get(i + 1), segment.get(i + 2)).toString())
                        && _map.getLabel(segment.get(i), segment.get(i + 1)).direction().fullName()
                        .equals(_map.getLabel(segment.get(i + 1), segment.get(i + 2)).direction()
                            .fullName())) {
                    dist = dist
                    + _map.getLabel(segment.get(i + 1), segment.get(i + 2)).length();
                    i += 1;
                    if (i > segment.size() - 3) {
                        break;
                    }
                }
    
            Road road = _map.getLabel(segment.get(i), segment.get(i + 1));
            DecimalFormat numberFormat = new DecimalFormat("#.0");
            Double roundDist = Math.round(dist * 10.0) / 10.0;
            System.out.printf("%d. Take %s %s for %.1f miles.", seq, road.toString(),
                road.direction().fullName(), roundDist);
            System.out.println(seq + ". Take " + road.toString() + " " + road.direction()
                .fullName() + " for " + numberFormat.format(roundDist) + " miles.");
            seq += 1;
            } else {
                Road road = _map.getLabel(segment.get(i), segment.get(i + 1));
                DecimalFormat numberFormat = new DecimalFormat("#.0");
                Double roadLength = Math.round(road.length() * 10.0) / 10.0;
                System.out.printf("%d. Take %s %s for %.1f miles.", seq, road.toString(),
                road.direction().fullName(), roundDist);
                System.out.println(seq + ". Take " + road.toString() + " " + road.direction()
                .fullName() + " for " + numberFormat.format(roadLength) + " miles.");
                seq += 1;
            }
        }
        return seq;
    }