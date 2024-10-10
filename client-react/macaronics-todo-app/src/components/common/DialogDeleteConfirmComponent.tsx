// src/components/common/DialogDeleteConfirmComponent.tsx

import React from "react";
import {
  Dialog,
  DialogHeader,
  DialogFooter,
  DialogContent,
  DialogTitle,
  DialogDescription, // DialogDescription 추가
} from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";

interface DialogDeleteConfirmComponentProps {
  isDialogOpen: boolean;
  setIsDialogOpen: React.Dispatch<React.SetStateAction<boolean>>;
  confirmDelete: () => void;
}

const DialogDeleteConfirmComponent: React.FC<DialogDeleteConfirmComponentProps> = ({
  isDialogOpen, 
  setIsDialogOpen,
  confirmDelete
}) => {
  return (
    <Dialog open={isDialogOpen} onOpenChange={setIsDialogOpen}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle className="text-lg font-bold">삭제 확인</DialogTitle>
          <DialogDescription className="mt-2">
            정말 삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다.
          </DialogDescription>
        </DialogHeader>
        <DialogFooter className="mt-4">
          <Button variant="ghost" onClick={() => setIsDialogOpen(false)}>
            취소
          </Button>
          <Button variant="destructive" onClick={confirmDelete}>
            삭제
          </Button> 
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
};

export default DialogDeleteConfirmComponent;
