

import UIKit

class RoomsViewController: UIViewController {

    
    @IBOutlet weak var locationLabel: UILabel!
    @IBOutlet weak var checkInLabel: UILabel!
    @IBOutlet weak var checkOutLabel: UILabel!
    @IBOutlet weak var numberOfPeople: UILabel!
    @IBOutlet weak var numberOfRooms: UILabel!
    @IBOutlet weak var roomsCollectionView: UICollectionView!
    
    
    private let information = Information.shared
    private var fetchRoomManager = FetchRoomManager()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureLabel()
        NotificationCenter.default.addObserver(self, selector: #selector(reloadCollectionView), name: Notification.Name.fetchRoom, object: nil)
        
        roomsCollectionView.register(RoomCollectionViewCell.nib, forCellWithReuseIdentifier: RoomCollectionViewCell.identifier)
    }
    
    func configureLabel()  {
        self.locationLabel.text = information.getName()
        self.checkInLabel.text = information.getCheckIn()
        self.checkOutLabel.text = information.getCheckOut()
        self.numberOfPeople.text = "게스트 \(information.getNumberOfPeople())"
        self.numberOfRooms.text = "\(fetchRoomManager.rooms.getRoomsCount())개 이상의 숙소"
    }
    
    
    
    @objc func reloadCollectionView() {
        roomsCollectionView.reloadData()
    }
    
}

extension RoomsViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 4
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "RoomCollectionViewCell", for: indexPath) as! RoomCollectionViewCell
        return cell
    }
    
    
}
