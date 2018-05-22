customername	modality	ib_count	rnk
Chang Gung Memorial Hospital-Lin Ko	IS	22	1
INTERMED SERVICE SRL	IS	19	2



modality	region	age	systemcoveragelevelwarrantyc	ib_count
MR	USCAN	P3	Warranty	4
LCS	USCAN	P4	Warranty	5
AW	APAC	P3	HBS	648


@Value(value = "${milestone.details.by.orderNumber.lineNumber}")
	private String milestoneDetailsByOrderLine;

	public Boolean updateMilestone(String orderNumber, String lineNumber, Integer position, LocalDate expectedDate) {
		Boolean isUpdateDone = false;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("expectedDate", FormattingUtil.formatToDestinationString(expectedDate));
		paramMap.put("orderNumber", orderNumber);
		paramMap.put("lineNumber", lineNumber);
		paramMap.put("position", position);

		int result = template.update(milestoneUpdate, paramMap);

		if (result > 0) {
			isUpdateDone = true;
		}

		return isUpdateDone;
	}

	public List<Milestone> getMilestonesByOrderLine(String orderNumber, String lineNumber) {

		List<Milestone> milestoneList = new ArrayList<>();
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("orderNumber", orderNumber);
		paramMap.put("lineNumber", lineNumber);
		milestoneList = template.query(milestoneDetailsByOrderLine, paramMap, new RowMapper<Milestone>() {

			@Override
			public Milestone mapRow(ResultSet rs, int rowNum) throws SQLException {
				Milestone result = new Milestone();
				result.setId(rs.getInt("id"));
				result.setMilestone(rs.getString("milestone"));
				result.setExpectedDate(rs.getString("expected_date"));
				result.setActualDate(rs.getString("actual_date"));
				result.setOrderNumber(rs.getString("order_number"));
				result.setLineNumber(rs.getString("line_number"));
				result.setStatus(rs.getString("status"));
				result.setViewer(rs.getString("viewer"));
				result.setCycle(rs.getString("cycle"));
				result.setPosition(rs.getInt("position"));
				// result.setType(rs.getString("type"));
				return result;
			}
		});
		return milestoneList;
	}